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
import { getEntity, updateEntity, createEntity, reset } from './achievement-dictionary.reducer';
import { IAchievementDictionary } from 'app/shared/model/achievement-dictionary.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAchievementDictionaryUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IAchievementDictionaryUpdateState {
  isNew: boolean;
  userDetailsId: string;
}

export class AchievementDictionaryUpdate extends React.Component<IAchievementDictionaryUpdateProps, IAchievementDictionaryUpdateState> {
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
      const { achievementDictionaryEntity } = this.props;
      const entity = {
        ...achievementDictionaryEntity,
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
    this.props.history.push('/entity/achievement-dictionary');
  };

  render() {
    const { achievementDictionaryEntity, userDetails, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="makamApp.achievementDictionary.home.createOrEditLabel">
              <Translate contentKey="makamApp.achievementDictionary.home.createOrEditLabel">
                Create or edit a AchievementDictionary
              </Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : achievementDictionaryEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="achievement-dictionary-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="achievement-dictionary-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="keyLabel" for="achievement-dictionary-key">
                    <Translate contentKey="makamApp.achievementDictionary.key">Key</Translate>
                  </Label>
                  <AvField id="achievement-dictionary-key" type="text" name="key" />
                </AvGroup>
                <AvGroup>
                  <Label id="valueLabel" for="achievement-dictionary-value">
                    <Translate contentKey="makamApp.achievementDictionary.value">Value</Translate>
                  </Label>
                  <AvField id="achievement-dictionary-value" type="text" name="value" />
                </AvGroup>
                <AvGroup>
                  <Label id="enabledLabel" check>
                    <AvInput id="achievement-dictionary-enabled" type="checkbox" className="form-control" name="enabled" />
                    <Translate contentKey="makamApp.achievementDictionary.enabled">Enabled</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="descriptionLabel" for="achievement-dictionary-description">
                    <Translate contentKey="makamApp.achievementDictionary.description">Description</Translate>
                  </Label>
                  <AvField id="achievement-dictionary-description" type="text" name="description" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/achievement-dictionary" replace color="info">
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
  achievementDictionaryEntity: storeState.achievementDictionary.entity,
  loading: storeState.achievementDictionary.loading,
  updating: storeState.achievementDictionary.updating,
  updateSuccess: storeState.achievementDictionary.updateSuccess
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
)(AchievementDictionaryUpdate);
