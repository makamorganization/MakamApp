import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './course.reducer';
// tslint:disable-next-line:no-unused-variable
import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICourseProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Course extends React.Component<ICourseProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { courseList, match } = this.props;
    return (
      <div>
        <h2 id="course-heading">
          <Translate contentKey="makamApp.course.home.title">Courses</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="makamApp.course.home.createLabel">Create new Course</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.course.title">Title</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.course.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.course.courseStartDate">Course Start Date</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.course.courseEndDate">Course End Date</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.course.registerStartDate">Register Start Date</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.course.registerEndDate">Register End Date</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.course.duration">Duration</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.course.maximumNumberOfParticipants">Maximum Number Of Participants</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.course.minimalNumberOfParticipants">Minimal Number Of Participants</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.course.lecturerName">Lecturer Name</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.course.lecturerSurname">Lecturer Surname</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.course.pointPerCourse">Point Per Course</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.course.isVisibleInApp">Is Visible In App</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {courseList.map((course, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${course.id}`} color="link" size="sm">
                      {course.id}
                    </Button>
                  </td>
                  <td>{course.title}</td>
                  <td>{course.description}</td>
                  <td>
                    <TextFormat type="date" value={course.courseStartDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={course.courseEndDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={course.registerStartDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={course.registerEndDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{course.duration}</td>
                  <td>{course.maximumNumberOfParticipants}</td>
                  <td>{course.minimalNumberOfParticipants}</td>
                  <td>{course.lecturerName}</td>
                  <td>{course.lecturerSurname}</td>
                  <td>{course.pointPerCourse}</td>
                  <td>
                    {course.isVisibleInApp ? (
                      <Translate contentKey="makamApp.course.yes">yes</Translate>
                    ) : (
                      <Translate contentKey="makamApp.course.no">no</Translate>
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${course.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${course.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${course.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ course }: IRootState) => ({
  courseList: course.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Course);
