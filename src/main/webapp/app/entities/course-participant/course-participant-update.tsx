import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ICourse } from 'app/shared/model/course.model';
import { getEntities as getCourses } from 'app/entities/course/course.reducer';
import { IUserDetails } from 'app/shared/model/user-details.model';
import { getEntities as getUserDetails } from 'app/entities/user-details/user-details.reducer';
import { getEntity, updateEntity, createEntity, reset } from './course-participant.reducer';
import { ICourseParticipant } from 'app/shared/model/course-participant.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICourseParticipantUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ICourseParticipantUpdateState {
  isNew: boolean;
  courseId: string;
  userId: string;
}

export class CourseParticipantUpdate extends React.Component<ICourseParticipantUpdateProps, ICourseParticipantUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      courseId: '0',
      userId: '0',
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

    this.props.getCourses();
    this.props.getUserDetails();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { courseParticipantEntity } = this.props;
      const entity = {
        ...courseParticipantEntity,
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
    this.props.history.push('/entity/course-participant');
  };

  render() {
    const { courseParticipantEntity, courses, userDetails, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="makamApp.courseParticipant.home.createOrEditLabel">
              <Translate contentKey="makamApp.courseParticipant.home.createOrEditLabel">Create or edit a CourseParticipant</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : courseParticipantEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="course-participant-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="course-participant-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="isUserPresentLabel" check>
                    <AvInput id="course-participant-isUserPresent" type="checkbox" className="form-control" name="isUserPresent" />
                    <Translate contentKey="makamApp.courseParticipant.isUserPresent">Is User Present</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="isUserLateLabel" check>
                    <AvInput id="course-participant-isUserLate" type="checkbox" className="form-control" name="isUserLate" />
                    <Translate contentKey="makamApp.courseParticipant.isUserLate">Is User Late</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="canCancelCourseAttendanceLabel" check>
                    <AvInput
                      id="course-participant-canCancelCourseAttendance"
                      type="checkbox"
                      className="form-control"
                      name="canCancelCourseAttendance"
                    />
                    <Translate contentKey="makamApp.courseParticipant.canCancelCourseAttendance">Can Cancel Course Attendance</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label for="course-participant-course">
                    <Translate contentKey="makamApp.courseParticipant.course">Course</Translate>
                  </Label>
                  <AvInput id="course-participant-course" type="select" className="form-control" name="courseId">
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
                <AvGroup>
                  <Label for="course-participant-user">
                    <Translate contentKey="makamApp.courseParticipant.user">User</Translate>
                  </Label>
                  <AvInput id="course-participant-user" type="select" className="form-control" name="userId">
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
                <Button tag={Link} id="cancel-save" to="/entity/course-participant" replace color="info">
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
  courses: storeState.course.entities,
  userDetails: storeState.userDetails.entities,
  courseParticipantEntity: storeState.courseParticipant.entity,
  loading: storeState.courseParticipant.loading,
  updating: storeState.courseParticipant.updating,
  updateSuccess: storeState.courseParticipant.updateSuccess
});

const mapDispatchToProps = {
  getCourses,
  getUserDetails,
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
)(CourseParticipantUpdate);
