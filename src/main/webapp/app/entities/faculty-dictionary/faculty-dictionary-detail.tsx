import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './faculty-dictionary.reducer';
import { IFacultyDictionary } from 'app/shared/model/faculty-dictionary.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFacultyDictionaryDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class FacultyDictionaryDetail extends React.Component<IFacultyDictionaryDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { facultyDictionaryEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="makamApp.facultyDictionary.detail.title">FacultyDictionary</Translate> [
            <b>{facultyDictionaryEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="key">
                <Translate contentKey="makamApp.facultyDictionary.key">Key</Translate>
              </span>
            </dt>
            <dd>{facultyDictionaryEntity.key}</dd>
            <dt>
              <span id="value">
                <Translate contentKey="makamApp.facultyDictionary.value">Value</Translate>
              </span>
            </dt>
            <dd>{facultyDictionaryEntity.value}</dd>
          </dl>
          <Button tag={Link} to="/entity/faculty-dictionary" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/faculty-dictionary/${facultyDictionaryEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ facultyDictionary }: IRootState) => ({
  facultyDictionaryEntity: facultyDictionary.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FacultyDictionaryDetail);
