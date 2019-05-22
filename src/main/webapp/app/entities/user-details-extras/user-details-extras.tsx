import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './user-details-extras.reducer';
import { IUserDetailsExtras } from 'app/shared/model/user-details-extras.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IUserDetailsExtrasProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class UserDetailsExtras extends React.Component<IUserDetailsExtrasProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { userDetailsExtrasList, match } = this.props;
    return (
      <div>
        <h2 id="user-details-extras-heading">
          <Translate contentKey="makamApp.userDetailsExtras.home.title">User Details Extras</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="makamApp.userDetailsExtras.home.createLabel">Create new User Details Extras</Translate>
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
                  <Translate contentKey="makamApp.userDetailsExtras.numberOfPoints">Number Of Points</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.userDetailsExtras.numberOfBeingLateInRow">Number Of Being Late In Row</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.userDetailsExtras.numberOfCoursesFinished">Number Of Courses Finished</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.userDetailsExtras.numberOfBeingLateTotal">Number Of Being Late Total</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.userDetailsExtras.numberOfCoursesTotalOmited">Number Of Courses Total Omited</Translate>
                </th>
                <th>
                  <Translate contentKey="makamApp.userDetailsExtras.numberOfCoursesOmitedInRow">Number Of Courses Omited In Row</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {userDetailsExtrasList.map((userDetailsExtras, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${userDetailsExtras.id}`} color="link" size="sm">
                      {userDetailsExtras.id}
                    </Button>
                  </td>
                  <td>{userDetailsExtras.numberOfPoints}</td>
                  <td>{userDetailsExtras.numberOfBeingLateInRow}</td>
                  <td>{userDetailsExtras.numberOfCoursesFinished}</td>
                  <td>{userDetailsExtras.numberOfBeingLateTotal}</td>
                  <td>{userDetailsExtras.numberOfCoursesTotalOmited}</td>
                  <td>{userDetailsExtras.numberOfCoursesOmitedInRow}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${userDetailsExtras.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${userDetailsExtras.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${userDetailsExtras.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ userDetailsExtras }: IRootState) => ({
  userDetailsExtrasList: userDetailsExtras.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(UserDetailsExtras);
