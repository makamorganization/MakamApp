import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './achievement-dictionary.reducer';
import { IAchievementDictionary } from 'app/shared/model/achievement-dictionary.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAchievementDictionaryDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class AchievementDictionaryDetail extends React.Component<IAchievementDictionaryDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { achievementDictionaryEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="makamApp.achievementDictionary.detail.title">AchievementDictionary</Translate> [
            <b>{achievementDictionaryEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="key">
                <Translate contentKey="makamApp.achievementDictionary.key">Key</Translate>
              </span>
            </dt>
            <dd>{achievementDictionaryEntity.key}</dd>
            <dt>
              <span id="value">
                <Translate contentKey="makamApp.achievementDictionary.value">Value</Translate>
              </span>
            </dt>
            <dd>{achievementDictionaryEntity.value}</dd>
            <dt>
              <span id="enabled">
                <Translate contentKey="makamApp.achievementDictionary.enabled">Enabled</Translate>
              </span>
            </dt>
            <dd>{achievementDictionaryEntity.enabled ? 'true' : 'false'}</dd>
            <dt>
              <span id="description">
                <Translate contentKey="makamApp.achievementDictionary.description">Description</Translate>
              </span>
            </dt>
            <dd>{achievementDictionaryEntity.description}</dd>
          </dl>
          <Button tag={Link} to="/entity/achievement-dictionary" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/achievement-dictionary/${achievementDictionaryEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ achievementDictionary }: IRootState) => ({
  achievementDictionaryEntity: achievementDictionary.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AchievementDictionaryDetail);
