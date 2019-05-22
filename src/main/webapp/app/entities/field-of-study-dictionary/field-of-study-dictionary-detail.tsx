import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './field-of-study-dictionary.reducer';
import { IFieldOfStudyDictionary } from 'app/shared/model/field-of-study-dictionary.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFieldOfStudyDictionaryDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class FieldOfStudyDictionaryDetail extends React.Component<IFieldOfStudyDictionaryDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { fieldOfStudyDictionaryEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="makamApp.fieldOfStudyDictionary.detail.title">FieldOfStudyDictionary</Translate> [
            <b>{fieldOfStudyDictionaryEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="key">
                <Translate contentKey="makamApp.fieldOfStudyDictionary.key">Key</Translate>
              </span>
            </dt>
            <dd>{fieldOfStudyDictionaryEntity.key}</dd>
            <dt>
              <span id="value">
                <Translate contentKey="makamApp.fieldOfStudyDictionary.value">Value</Translate>
              </span>
            </dt>
            <dd>{fieldOfStudyDictionaryEntity.value}</dd>
            <dt>
              <Translate contentKey="makamApp.fieldOfStudyDictionary.facultyDictionary">Faculty Dictionary</Translate>
            </dt>
            <dd>{fieldOfStudyDictionaryEntity.facultyDictionaryId ? fieldOfStudyDictionaryEntity.facultyDictionaryId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/field-of-study-dictionary" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/field-of-study-dictionary/${fieldOfStudyDictionaryEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ fieldOfStudyDictionary }: IRootState) => ({
  fieldOfStudyDictionaryEntity: fieldOfStudyDictionary.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FieldOfStudyDictionaryDetail);
