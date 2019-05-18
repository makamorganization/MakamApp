import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './faculty-dictionary.reducer';
import { IFacultyDictionary } from 'app/shared/model/faculty-dictionary.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFacultyDictionaryProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class FacultyDictionary extends React.Component<IFacultyDictionaryProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { facultyDictionaryList, match } = this.props;
    return (
      <div>
        <h2 id="faculty-dictionary-heading">
          <Translate contentKey="makamApp.facultyDictionary.home.title">Faculty Dictionaries</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="makamApp.facultyDictionary.home.createLabel">Create new Faculty Dictionary</Translate>
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
                  <Translate contentKey="makamApp.facultyDictionary.key">Key</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.facultyDictionary.value">Value</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {facultyDictionaryList.map((facultyDictionary, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${facultyDictionary.id}`} color="link" size="sm">
                      {facultyDictionary.id}
                    </Button>
                  </td>
                  <td>{facultyDictionary.key}</td>
                  <td>{facultyDictionary.value}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${facultyDictionary.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${facultyDictionary.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${facultyDictionary.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ facultyDictionary }: IRootState) => ({
  facultyDictionaryList: facultyDictionary.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FacultyDictionary);
