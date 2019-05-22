import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './course.reducer';
import { ICourse } from 'app/shared/model/course.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICourseDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class CourseDetail extends React.Component<ICourseDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { courseEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="makamApp.course.detail.title">Course</Translate> [<b>{courseEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="title">
                <Translate contentKey="makamApp.course.title">Title</Translate>
              </span>
            </dt>
            <dd>{courseEntity.title}</dd>
            <dt>
              <span id="description">
                <Translate contentKey="makamApp.course.description">Description</Translate>
              </span>
            </dt>
            <dd>{courseEntity.description}</dd>
            <dt>
              <span id="courseStartDate">
                <Translate contentKey="makamApp.course.courseStartDate">Course Start Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={courseEntity.courseStartDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="courseEndDate">
                <Translate contentKey="makamApp.course.courseEndDate">Course End Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={courseEntity.courseEndDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="registerStartDate">
                <Translate contentKey="makamApp.course.registerStartDate">Register Start Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={courseEntity.registerStartDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="registerEndDate">
                <Translate contentKey="makamApp.course.registerEndDate">Register End Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={courseEntity.registerEndDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="duration">
                <Translate contentKey="makamApp.course.duration">Duration</Translate>
              </span>
            </dt>
            <dd>{courseEntity.duration}</dd>
            <dt>
              <span id="maximumNumberOfParticipants">
                <Translate contentKey="makamApp.course.maximumNumberOfParticipants">Maximum Number Of Participants</Translate>
              </span>
            </dt>
            <dd>{courseEntity.maximumNumberOfParticipants}</dd>
            <dt>
              <span id="minimalNumberOfParticipants">
                <Translate contentKey="makamApp.course.minimalNumberOfParticipants">Minimal Number Of Participants</Translate>
              </span>
            </dt>
            <dd>{courseEntity.minimalNumberOfParticipants}</dd>
            <dt>
              <span id="lecturerName">
                <Translate contentKey="makamApp.course.lecturerName">Lecturer Name</Translate>
              </span>
            </dt>
            <dd>{courseEntity.lecturerName}</dd>
            <dt>
              <span id="lecturerSurname">
                <Translate contentKey="makamApp.course.lecturerSurname">Lecturer Surname</Translate>
              </span>
            </dt>
            <dd>{courseEntity.lecturerSurname}</dd>
            <dt>
              <span id="pointPerCourse">
                <Translate contentKey="makamApp.course.pointPerCourse">Point Per Course</Translate>
              </span>
            </dt>
            <dd>{courseEntity.pointPerCourse}</dd>
            <dt>
              <span id="isVisibleInApp">
                <Translate contentKey="makamApp.course.isVisibleInApp">Is Visible In App</Translate>
              </span>
            </dt>
            <dd>{courseEntity.isVisibleInApp ? 'true' : 'false'}</dd>
          </dl>
          <Button tag={Link} to="/entity/course" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/course/${courseEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ course }: IRootState) => ({
  courseEntity: course.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CourseDetail);
