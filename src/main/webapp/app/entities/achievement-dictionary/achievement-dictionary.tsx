import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './achievement-dictionary.reducer';
import { IAchievementDictionary } from 'app/shared/model/achievement-dictionary.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAchievementDictionaryProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class AchievementDictionary extends React.Component<IAchievementDictionaryProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { achievementDictionaryList, match } = this.props;
    return (
      <div>
        <h2 id="achievement-dictionary-heading">
          <Translate contentKey="makamApp.achievementDictionary.home.title">Achievement Dictionaries</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="makamApp.achievementDictionary.home.createLabel">Create new Achievement Dictionary</Translate>
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
                  <Translate contentKey="makamApp.achievementDictionary.key">Key</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.achievementDictionary.value">Value</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.achievementDictionary.enabled">Enabled</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.achievementDictionary.description">Description</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {achievementDictionaryList.map((achievementDictionary, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${achievementDictionary.id}`} color="link" size="sm">
                      {achievementDictionary.id}
                    </Button>
                  </td>
                  <td>{achievementDictionary.key}</td>
                  <td>{achievementDictionary.value}</td>
                  <td>{achievementDictionary.enabled ? 'true' : 'false'}</td>
                  <td>{achievementDictionary.description}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${achievementDictionary.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${achievementDictionary.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${achievementDictionary.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ achievementDictionary }: IRootState) => ({
  achievementDictionaryList: achievementDictionary.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AchievementDictionary);
