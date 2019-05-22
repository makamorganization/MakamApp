import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IUserDetailsExtras } from 'app/shared/model/user-details-extras.model';
import { getEntities as getUserDetailsExtras } from 'app/entities/user-details-extras/user-details-extras.reducer';
import { IAchievementDictionary } from 'app/shared/model/achievement-dictionary.model';
import { getEntities as getAchievementDictionaries } from 'app/entities/achievement-dictionary/achievement-dictionary.reducer';
import { ICourseParticipant } from 'app/shared/model/course-participant.model';
import { getEntities as getCourseParticipants } from 'app/entities/course-participant/course-participant.reducer';
import { getEntity, updateEntity, createEntity, reset } from './user-details.reducer';
import { IUserDetails } from 'app/shared/model/user-details.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IUserDetailsUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IUserDetailsUpdateState {
  isNew: boolean;
  idsachievementDictionary: any[];
  userDetailsExtrasId: string;
  courseParticipantId: string;
}

export class UserDetailsUpdate extends React.Component<IUserDetailsUpdateProps, IUserDetailsUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      idsachievementDictionary: [],
      userDetailsExtrasId: '0',
      courseParticipantId: '0',
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

    this.props.getUserDetailsExtras();
    this.props.getAchievementDictionaries();
    this.props.getCourseParticipants();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { userDetailsEntity } = this.props;
      const entity = {
        ...userDetailsEntity,
        ...values,
        achievementDictionaries: mapIdList(values.achievementDictionaries)
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/user-details');
  };

  render() {
    const { userDetailsEntity, userDetailsExtras, achievementDictionaries, courseParticipants, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="makamApp.userDetails.home.createOrEditLabel">
              <Translate contentKey="makamApp.userDetails.home.createOrEditLabel">Create or edit a UserDetails</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : userDetailsEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="user-details-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="user-details-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="studentCardNumberLabel" for="user-details-studentCardNumber">
                    <Translate contentKey="makamApp.userDetails.studentCardNumber">Student Card Number</Translate>
                  </Label>
                  <AvField id="user-details-studentCardNumber" type="string" className="form-control" name="studentCardNumber" />
                </AvGroup>
                <AvGroup>
                  <Label id="nameLabel" for="user-details-name">
                    <Translate contentKey="makamApp.userDetails.name">Name</Translate>
                  </Label>
                  <AvField id="user-details-name" type="text" name="name" />
                </AvGroup>
                <AvGroup>
                  <Label id="surnameLabel" for="user-details-surname">
                    <Translate contentKey="makamApp.userDetails.surname">Surname</Translate>
                  </Label>
                  <AvField id="user-details-surname" type="text" name="surname" />
                </AvGroup>
                <AvGroup>
                  <Label id="telephoneNumberLabel" for="user-details-telephoneNumber">
                    <Translate contentKey="makamApp.userDetails.telephoneNumber">Telephone Number</Translate>
                  </Label>
                  <AvField id="user-details-telephoneNumber" type="text" name="telephoneNumber" />
                </AvGroup>
                <AvGroup>
                  <Label id="studyYearLabel" for="user-details-studyYear">
                    <Translate contentKey="makamApp.userDetails.studyYear">Study Year</Translate>
                  </Label>
                  <AvField id="user-details-studyYear" type="string" className="form-control" name="studyYear" />
                </AvGroup>
                <AvGroup>
                  <Label id="facultyLabel" for="user-details-faculty">
                    <Translate contentKey="makamApp.userDetails.faculty">Faculty</Translate>
                  </Label>
                  <AvField id="user-details-faculty" type="text" name="faculty" />
                </AvGroup>
                <AvGroup>
                  <Label id="fieldOfStudyLabel" for="user-details-fieldOfStudy">
                    <Translate contentKey="makamApp.userDetails.fieldOfStudy">Field Of Study</Translate>
                  </Label>
                  <AvField id="user-details-fieldOfStudy" type="text" name="fieldOfStudy" />
                </AvGroup>
                <AvGroup>
                  <Label for="user-details-userDetailsExtras">
                    <Translate contentKey="makamApp.userDetails.userDetailsExtras">User Details Extras</Translate>
                  </Label>
                  <AvInput id="user-details-userDetailsExtras" type="select" className="form-control" name="userDetailsExtrasId">
                    <option value="" key="0" />
                    {userDetailsExtras
                      ? userDetailsExtras.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="user-details-achievementDictionary">
                    <Translate contentKey="makamApp.userDetails.achievementDictionary">Achievement Dictionary</Translate>
                  </Label>
                  <AvInput
                    id="user-details-achievementDictionary"
                    type="select"
                    multiple
                    className="form-control"
                    name="achievementDictionaries"
                    value={userDetailsEntity.achievementDictionaries && userDetailsEntity.achievementDictionaries.map(e => e.id)}
                  >
                    <option value="" key="0" />
                    {achievementDictionaries
                      ? achievementDictionaries.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/user-details" replace color="info">
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
  userDetailsExtras: storeState.userDetailsExtras.entities,
  achievementDictionaries: storeState.achievementDictionary.entities,
  courseParticipants: storeState.courseParticipant.entities,
  userDetailsEntity: storeState.userDetails.entity,
  loading: storeState.userDetails.loading,
  updating: storeState.userDetails.updating,
  updateSuccess: storeState.userDetails.updateSuccess
});

const mapDispatchToProps = {
  getUserDetailsExtras,
  getAchievementDictionaries,
  getCourseParticipants,
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
)(UserDetailsUpdate);
