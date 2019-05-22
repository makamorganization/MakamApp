import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IUserDetails } from 'app/shared/model/user-details.model';
import { getEntities as getUserDetails } from 'app/entities/user-details/user-details.reducer';
import { getEntity, updateEntity, createEntity, reset } from './user-details-extras.reducer';
import { IUserDetailsExtras } from 'app/shared/model/user-details-extras.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IUserDetailsExtrasUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IUserDetailsExtrasUpdateState {
  isNew: boolean;
  userDetailsId: string;
}

export class UserDetailsExtrasUpdate extends React.Component<IUserDetailsExtrasUpdateProps, IUserDetailsExtrasUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      userDetailsId: '0',
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
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { userDetailsExtrasEntity } = this.props;
      const entity = {
        ...userDetailsExtrasEntity,
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
    this.props.history.push('/entity/user-details-extras');
  };

  render() {
    const { userDetailsExtrasEntity, userDetails, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="makamApp.userDetailsExtras.home.createOrEditLabel">
              <Translate contentKey="makamApp.userDetailsExtras.home.createOrEditLabel">Create or edit a UserDetailsExtras</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : userDetailsExtrasEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="user-details-extras-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="user-details-extras-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="numberOfPointsLabel" for="user-details-extras-numberOfPoints">
                    <Translate contentKey="makamApp.userDetailsExtras.numberOfPoints">Number Of Points</Translate>
                  </Label>
                  <AvField id="user-details-extras-numberOfPoints" type="string" className="form-control" name="numberOfPoints" />
                </AvGroup>
                <AvGroup>
                  <Label id="numberOfBeingLateInRowLabel" for="user-details-extras-numberOfBeingLateInRow">
                    <Translate contentKey="makamApp.userDetailsExtras.numberOfBeingLateInRow">Number Of Being Late In Row</Translate>
                  </Label>
                  <AvField
                    id="user-details-extras-numberOfBeingLateInRow"
                    type="string"
                    className="form-control"
                    name="numberOfBeingLateInRow"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="numberOfCoursesFinishedLabel" for="user-details-extras-numberOfCoursesFinished">
                    <Translate contentKey="makamApp.userDetailsExtras.numberOfCoursesFinished">Number Of Courses Finished</Translate>
                  </Label>
                  <AvField
                    id="user-details-extras-numberOfCoursesFinished"
                    type="string"
                    className="form-control"
                    name="numberOfCoursesFinished"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="numberOfBeingLateTotalLabel" for="user-details-extras-numberOfBeingLateTotal">
                    <Translate contentKey="makamApp.userDetailsExtras.numberOfBeingLateTotal">Number Of Being Late Total</Translate>
                  </Label>
                  <AvField
                    id="user-details-extras-numberOfBeingLateTotal"
                    type="string"
                    className="form-control"
                    name="numberOfBeingLateTotal"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="numberOfCoursesTotalOmitedLabel" for="user-details-extras-numberOfCoursesTotalOmited">
                    <Translate contentKey="makamApp.userDetailsExtras.numberOfCoursesTotalOmited">Number Of Courses Total Omited</Translate>
                  </Label>
                  <AvField
                    id="user-details-extras-numberOfCoursesTotalOmited"
                    type="string"
                    className="form-control"
                    name="numberOfCoursesTotalOmited"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="numberOfCoursesOmitedInRowLabel" for="user-details-extras-numberOfCoursesOmitedInRow">
                    <Translate contentKey="makamApp.userDetailsExtras.numberOfCoursesOmitedInRow">
                      Number Of Courses Omited In Row
                    </Translate>
                  </Label>
                  <AvField
                    id="user-details-extras-numberOfCoursesOmitedInRow"
                    type="string"
                    className="form-control"
                    name="numberOfCoursesOmitedInRow"
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/user-details-extras" replace color="info">
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
  userDetailsExtrasEntity: storeState.userDetailsExtras.entity,
  loading: storeState.userDetailsExtras.loading,
  updating: storeState.userDetailsExtras.updating,
  updateSuccess: storeState.userDetailsExtras.updateSuccess
});

const mapDispatchToProps = {
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
)(UserDetailsExtrasUpdate);
