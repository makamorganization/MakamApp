import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './user-details.reducer';
import { IUserDetails } from 'app/shared/model/user-details.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IUserDetailsProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class UserDetails extends React.Component<IUserDetailsProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { userDetailsList, match } = this.props;
    return (
      <div>
        <h2 id="user-details-heading">
          <Translate contentKey="makamApp.userDetails.home.title">User Details</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="makamApp.userDetails.home.createLabel">Create new User Details</Translate>
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
                  <Translate contentKey="makamApp.userDetails.studentCardNumber">Student Card Number</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.userDetails.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.userDetails.surname">Surname</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.userDetails.telephoneNumber">Telephone Number</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.userDetails.studyYear">Study Year</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.userDetails.faculty">Faculty</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.userDetails.fieldOfStudy">Field Of Study</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.userDetails.userDetailsExtras">User Details Extras</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.userDetails.achievementDictionary">Achievement Dictionary</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {userDetailsList.map((userDetails, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${userDetails.id}`} color="link" size="sm">
                      {userDetails.id}
                    </Button>
                  </td>
                  <td>{userDetails.studentCardNumber}</td>
                  <td>{userDetails.name}</td>
                  <td>{userDetails.surname}</td>
                  <td>{userDetails.telephoneNumber}</td>
                  <td>{userDetails.studyYear}</td>
                  <td>{userDetails.faculty}</td>
                  <td>{userDetails.fieldOfStudy}</td>
                  <td>
                    {userDetails.userDetailsExtrasId ? (
                      <Link to={`user-details-extras/${userDetails.userDetailsExtrasId}`}>{userDetails.userDetailsExtrasId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {userDetails.achievementDictionaries
                      ? userDetails.achievementDictionaries.map((val, j) => (
                          <span key={j}>
                            <Link to={`achievement-dictionary/${val.id}`}>{val.id}</Link>
                            {j === userDetails.achievementDictionaries.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${userDetails.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${userDetails.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${userDetails.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ userDetails }: IRootState) => ({
  userDetailsList: userDetails.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(UserDetails);
