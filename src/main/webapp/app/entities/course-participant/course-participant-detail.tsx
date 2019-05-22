import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './course-participant.reducer';
import { ICourseParticipant } from 'app/shared/model/course-participant.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICourseParticipantDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class CourseParticipantDetail extends React.Component<ICourseParticipantDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { courseParticipantEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="makamApp.courseParticipant.detail.title">CourseParticipant</Translate> [
            <b>{courseParticipantEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="isUserPresent">
                <Translate contentKey="makamApp.courseParticipant.isUserPresent">Is User Present</Translate>
              </span>
            </dt>
            <dd>{courseParticipantEntity.isUserPresent ? 'true' : 'false'}</dd>
            <dt>
              <span id="isUserLate">
                <Translate contentKey="makamApp.courseParticipant.isUserLate">Is User Late</Translate>
              </span>
            </dt>
            <dd>{courseParticipantEntity.isUserLate ? 'true' : 'false'}</dd>
            <dt>
              <span id="canCancelCourseAttendance">
                <Translate contentKey="makamApp.courseParticipant.canCancelCourseAttendance">Can Cancel Course Attendance</Translate>
              </span>
            </dt>
            <dd>{courseParticipantEntity.canCancelCourseAttendance ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="makamApp.courseParticipant.course">Course</Translate>
            </dt>
            <dd>{courseParticipantEntity.courseId ? courseParticipantEntity.courseId : ''}</dd>
            <dt>
              <Translate contentKey="makamApp.courseParticipant.user">User</Translate>
            </dt>
            <dd>{courseParticipantEntity.userId ? courseParticipantEntity.userId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/course-participant" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/course-participant/${courseParticipantEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ courseParticipant }: IRootState) => ({
  courseParticipantEntity: courseParticipant.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CourseParticipantDetail);
