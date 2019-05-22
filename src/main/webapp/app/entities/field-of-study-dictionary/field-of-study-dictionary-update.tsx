import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IFacultyDictionary } from 'app/shared/model/faculty-dictionary.model';
import { getEntities as getFacultyDictionaries } from 'app/entities/faculty-dictionary/faculty-dictionary.reducer';
import { getEntity, updateEntity, createEntity, reset } from './field-of-study-dictionary.reducer';
import { IFieldOfStudyDictionary } from 'app/shared/model/field-of-study-dictionary.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IFieldOfStudyDictionaryUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IFieldOfStudyDictionaryUpdateState {
  isNew: boolean;
  facultyDictionaryId: string;
}

export class FieldOfStudyDictionaryUpdate extends React.Component<IFieldOfStudyDictionaryUpdateProps, IFieldOfStudyDictionaryUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      facultyDictionaryId: '0',
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

    this.props.getFacultyDictionaries();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { fieldOfStudyDictionaryEntity } = this.props;
      const entity = {
        ...fieldOfStudyDictionaryEntity,
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
    this.props.history.push('/entity/field-of-study-dictionary');
  };

  render() {
    const { fieldOfStudyDictionaryEntity, facultyDictionaries, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="makamApp.fieldOfStudyDictionary.home.createOrEditLabel">
              <Translate contentKey="makamApp.fieldOfStudyDictionary.home.createOrEditLabel">
                Create or edit a FieldOfStudyDictionary
              </Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : fieldOfStudyDictionaryEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="field-of-study-dictionary-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="field-of-study-dictionary-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="keyLabel" for="field-of-study-dictionary-key">
                    <Translate contentKey="makamApp.fieldOfStudyDictionary.key">Key</Translate>
                  </Label>
                  <AvField id="field-of-study-dictionary-key" type="text" name="key" />
                </AvGroup>
                <AvGroup>
                  <Label id="valueLabel" for="field-of-study-dictionary-value">
                    <Translate contentKey="makamApp.fieldOfStudyDictionary.value">Value</Translate>
                  </Label>
                  <AvField id="field-of-study-dictionary-value" type="text" name="value" />
                </AvGroup>
                <AvGroup>
                  <Label for="field-of-study-dictionary-facultyDictionary">
                    <Translate contentKey="makamApp.fieldOfStudyDictionary.facultyDictionary">Faculty Dictionary</Translate>
                  </Label>
                  <AvInput
                    id="field-of-study-dictionary-facultyDictionary"
                    type="select"
                    className="form-control"
                    name="facultyDictionaryId"
                  >
                    <option value="" key="0" />
                    {facultyDictionaries
                      ? facultyDictionaries.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/field-of-study-dictionary" replace color="info">
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
  facultyDictionaries: storeState.facultyDictionary.entities,
  fieldOfStudyDictionaryEntity: storeState.fieldOfStudyDictionary.entity,
  loading: storeState.fieldOfStudyDictionary.loading,
  updating: storeState.fieldOfStudyDictionary.updating,
  updateSuccess: storeState.fieldOfStudyDictionary.updateSuccess
});

const mapDispatchToProps = {
  getFacultyDictionaries,
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
)(FieldOfStudyDictionaryUpdate);
