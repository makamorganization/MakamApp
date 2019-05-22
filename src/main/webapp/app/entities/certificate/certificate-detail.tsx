import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './certificate.reducer';
import { ICertificate } from 'app/shared/model/certificate.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICertificateDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class CertificateDetail extends React.Component<ICertificateDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { certificateEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="makamApp.certificate.detail.title">Certificate</Translate> [<b>{certificateEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="title">
                <Translate contentKey="makamApp.certificate.title">Title</Translate>
              </span>
            </dt>
            <dd>{certificateEntity.title}</dd>
            <dt>
              <span id="path">
                <Translate contentKey="makamApp.certificate.path">Path</Translate>
              </span>
            </dt>
            <dd>{certificateEntity.path}</dd>
            <dt>
              <span id="validityEndDate">
                <Translate contentKey="makamApp.certificate.validityEndDate">Validity End Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={certificateEntity.validityEndDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="signature">
                <Translate contentKey="makamApp.certificate.signature">Signature</Translate>
              </span>
            </dt>
            <dd>
              {certificateEntity.signature ? (
                <div>
                  <a onClick={openFile(certificateEntity.signatureContentType, certificateEntity.signature)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                  <span>
                    {certificateEntity.signatureContentType}, {byteSize(certificateEntity.signature)}
                  </span>
                </div>
              ) : null}
            </dd>
            <dt>
              <Translate contentKey="makamApp.certificate.userDetails">User Details</Translate>
            </dt>
            <dd>{certificateEntity.userDetailsId ? certificateEntity.userDetailsId : ''}</dd>
            <dt>
              <Translate contentKey="makamApp.certificate.course">Course</Translate>
            </dt>
            <dd>{certificateEntity.courseId ? certificateEntity.courseId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/certificate" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/certificate/${certificateEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ certificate }: IRootState) => ({
  certificateEntity: certificate.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CertificateDetail);
