import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './field-of-study-dictionary.reducer';
import { IFieldOfStudyDictionary } from 'app/shared/model/field-of-study-dictionary.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFieldOfStudyDictionaryProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class FieldOfStudyDictionary extends React.Component<IFieldOfStudyDictionaryProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { fieldOfStudyDictionaryList, match } = this.props;
    return (
      <div>
        <h2 id="field-of-study-dictionary-heading">
          <Translate contentKey="makamApp.fieldOfStudyDictionary.home.title">Field Of Study Dictionaries</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="makamApp.fieldOfStudyDictionary.home.createLabel">Create new Field Of Study Dictionary</Translate>
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
                  <Translate contentKey="makamApp.fieldOfStudyDictionary.key">Key</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.fieldOfStudyDictionary.value">Value</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.fieldOfStudyDictionary.facultyDictionary">Faculty Dictionary</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {fieldOfStudyDictionaryList.map((fieldOfStudyDictionary, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${fieldOfStudyDictionary.id}`} color="link" size="sm">
                      {fieldOfStudyDictionary.id}
                    </Button>
                  </td>
                  <td>{fieldOfStudyDictionary.key}</td>
                  <td>{fieldOfStudyDictionary.value}</td>
                  <td>
                    {fieldOfStudyDictionary.facultyDictionaryId ? (
                      <Link to={`faculty-dictionary/${fieldOfStudyDictionary.facultyDictionaryId}`}>
                        {fieldOfStudyDictionary.facultyDictionaryId}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${fieldOfStudyDictionary.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${fieldOfStudyDictionary.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${fieldOfStudyDictionary.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ fieldOfStudyDictionary }: IRootState) => ({
  fieldOfStudyDictionaryList: fieldOfStudyDictionary.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FieldOfStudyDictionary);
