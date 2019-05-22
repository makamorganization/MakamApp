import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IUserDetails } from 'app/shared/model/user-details.model';
import { getEntities as getUserDetails } from 'app/entities/user-details/user-details.reducer';
import { ICourse } from 'app/shared/model/course.model';
import { getEntities as getCourses } from 'app/entities/course/course.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './certificate.reducer';
import { ICertificate } from 'app/shared/model/certificate.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICertificateUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ICertificateUpdateState {
  isNew: boolean;
  userDetailsId: string;
  courseId: string;
}

export class CertificateUpdate extends React.Component<ICertificateUpdateProps, ICertificateUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      userDetailsId: '0',
      courseId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getUserDetails();
    this.props.getCourses();
  }

  onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => this.props.setBlob(name, data, contentType), isAnImage);
  };

  clearBlob = name => () => {
    this.props.setBlob(name, undefined, undefined);
  };

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { certificateEntity } = this.props;
      const entity = {
        ...certificateEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/certificate');
  };

  render() {
    const { certificateEntity, userDetails, courses, loading, updating } = this.props;
    const { isNew } = this.state;

    const { signature, signatureContentType } = certificateEntity;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="makamApp.certificate.home.createOrEditLabel">
              <Translate contentKey="makamApp.certificate.home.createOrEditLabel">Create or edit a Certificate</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : certificateEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="certificate-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="certificate-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="titleLabel" for="certificate-title">
                    <Translate contentKey="makamApp.certificate.title">Title</Translate>
                  </Label>
                  <AvField id="certificate-title" type="text" name="title" />
                </AvGroup>
                <AvGroup>
                  <Label id="pathLabel" for="certificate-path">
                    <Translate contentKey="makamApp.certificate.path">Path</Translate>
                  </Label>
                  <AvField id="certificate-path" type="text" name="path" />
                </AvGroup>
                <AvGroup>
                  <Label id="validityEndDateLabel" for="certificate-validityEndDate">
                    <Translate contentKey="makamApp.certificate.validityEndDate">Validity End Date</Translate>
                  </Label>
                  <AvField id="certificate-validityEndDate" type="date" className="form-control" name="validityEndDate" />
                </AvGroup>
                <AvGroup>
                  <AvGroup>
                    <Label id="signatureLabel" for="signature">
                      <Translate contentKey="makamApp.certificate.signature">Signature</Translate>
                    </Label>
                    <br />
                    {signature ? (
                      <div>
                        <a onClick={openFile(signatureContentType, signature)}>
                          <Translate contentKey="entity.action.open">Open</Translate>
                        </a>
                        <br />
                        <Row>
                          <Col md="11">
                            <span>
                              {signatureContentType}, {byteSize(signature)}
                            </span>
                          </Col>
                          <Col md="1">
                            <Button color="danger" onClick={this.clearBlob('signature')}>
                              <FontAwesomeIcon icon="times-circle" />
                            </Button>
                          </Col>
                        </Row>
                      </div>
                    ) : null}
                    <input id="file_signature" type="file" onChange={this.onBlobChange(false, 'signature')} />
                    <AvInput type="hidden" name="signature" value={signature} />
                  </AvGroup>
                </AvGroup>
                <AvGroup>
                  <Label for="certificate-userDetails">
                    <Translate contentKey="makamApp.certificate.userDetails">User Details</Translate>
                  </Label>
                  <AvInput id="certificate-userDetails" type="select" className="form-control" name="userDetailsId">
                    <option value="" key="0" />
                    {userDetails
                      ? userDetails.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="certificate-course">
                    <Translate contentKey="makamApp.certificate.course">Course</Translate>
                  </Label>
                  <AvInput id="certificate-course" type="select" className="form-control" name="courseId">
                    <option value="" key="0" />
                    {courses
                      ? courses.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/certificate" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  userDetails: storeState.userDetails.entities,
  courses: storeState.course.entities,
  certificateEntity: storeState.certificate.entity,
  loading: storeState.certificate.loading,
  updating: storeState.certificate.updating,
  updateSuccess: storeState.certificate.updateSuccess
});

const mapDispatchToProps = {
  getUserDetails,
  getCourses,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CertificateUpdate);
