import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ICertificate } from 'app/shared/model/certificate.model';
import { getEntities as getCertificates } from 'app/entities/certificate/certificate.reducer';
import { getEntity, updateEntity, createEntity, reset } from './course.reducer';
import { ICourse } from 'app/shared/model/course.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICourseUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ICourseUpdateState {
  isNew: boolean;
  certificateId: string;
}

export class CourseUpdate extends React.Component<ICourseUpdateProps, ICourseUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      certificateId: '0',
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

    this.props.getCertificates();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { courseEntity } = this.props;
      const entity = {
        ...courseEntity,
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
    this.props.history.push('/entity/course');
  };

  render() {
    const { courseEntity, certificates, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="makamApp.course.home.createOrEditLabel">
              <Translate contentKey="makamApp.course.home.createOrEditLabel">Create or edit a Course</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : courseEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="course-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="course-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="titleLabel" for="course-title">
                    <Translate contentKey="makamApp.course.title">Title</Translate>
                  </Label>
                  <AvField id="course-title" type="text" name="title" />
                </AvGroup>
                <AvGroup>
                  <Label id="descriptionLabel" for="course-description">
                    <Translate contentKey="makamApp.course.description">Description</Translate>
                  </Label>
                  <AvField id="course-description" type="text" name="description" />
                </AvGroup>
                <AvGroup>
                  <Label id="courseStartDateLabel" for="course-courseStartDate">
                    <Translate contentKey="makamApp.course.courseStartDate">Course Start Date</Translate>
                  </Label>
                  <AvField id="course-courseStartDate" type="date" className="form-control" name="courseStartDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="courseEndDateLabel" for="course-courseEndDate">
                    <Translate contentKey="makamApp.course.courseEndDate">Course End Date</Translate>
                  </Label>
                  <AvField id="course-courseEndDate" type="date" className="form-control" name="courseEndDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="registerStartDateLabel" for="course-registerStartDate">
                    <Translate contentKey="makamApp.course.registerStartDate">Register Start Date</Translate>
                  </Label>
                  <AvField id="course-registerStartDate" type="date" className="form-control" name="registerStartDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="registerEndDateLabel" for="course-registerEndDate">
                    <Translate contentKey="makamApp.course.registerEndDate">Register End Date</Translate>
                  </Label>
                  <AvField id="course-registerEndDate" type="date" className="form-control" name="registerEndDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="durationLabel" for="course-duration">
                    <Translate contentKey="makamApp.course.duration">Duration</Translate>
                  </Label>
                  <AvField id="course-duration" type="string" className="form-control" name="duration" />
                </AvGroup>
                <AvGroup>
                  <Label id="maximumNumberOfParticipantsLabel" for="course-maximumNumberOfParticipants">
                    <Translate contentKey="makamApp.course.maximumNumberOfParticipants">Maximum Number Of Participants</Translate>
                  </Label>
                  <AvField
                    id="course-maximumNumberOfParticipants"
                    type="string"
                    className="form-control"
                    name="maximumNumberOfParticipants"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="minimalNumberOfParticipantsLabel" for="course-minimalNumberOfParticipants">
                    <Translate contentKey="makamApp.course.minimalNumberOfParticipants">Minimal Number Of Participants</Translate>
                  </Label>
                  <AvField
                    id="course-minimalNumberOfParticipants"
                    type="string"
                    className="form-control"
                    name="minimalNumberOfParticipants"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="lecturerNameLabel" for="course-lecturerName">
                    <Translate contentKey="makamApp.course.lecturerName">Lecturer Name</Translate>
                  </Label>
                  <AvField id="course-lecturerName" type="text" name="lecturerName" />
                </AvGroup>
                <AvGroup>
                  <Label id="lecturerSurnameLabel" for="course-lecturerSurname">
                    <Translate contentKey="makamApp.course.lecturerSurname">Lecturer Surname</Translate>
                  </Label>
                  <AvField id="course-lecturerSurname" type="text" name="lecturerSurname" />
                </AvGroup>
                <AvGroup>
                  <Label id="pointPerCourseLabel" for="course-pointPerCourse">
                    <Translate contentKey="makamApp.course.pointPerCourse">Point Per Course</Translate>
                  </Label>
                  <AvField id="course-pointPerCourse" type="string" className="form-control" name="pointPerCourse" />
                </AvGroup>
                <AvGroup>
                  <Label id="isVisibleInAppLabel" check>
                    <AvInput id="course-isVisibleInApp" type="checkbox" className="form-control" name="isVisibleInApp" />
                    <Translate contentKey="makamApp.course.isVisibleInApp">Is Visible In App</Translate>
                  </Label>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/course" replace color="info">
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
  certificates: storeState.certificate.entities,
  courseEntity: storeState.course.entity,
  loading: storeState.course.loading,
  updating: storeState.course.updating,
  updateSuccess: storeState.course.updateSuccess
});

const mapDispatchToProps = {
  getCertificates,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CourseUpdate);
