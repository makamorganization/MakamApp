import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './user-details-extras.reducer';
import { IUserDetailsExtras } from 'app/shared/model/user-details-extras.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IUserDetailsExtrasDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class UserDetailsExtrasDetail extends React.Component<IUserDetailsExtrasDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { userDetailsExtrasEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="makamApp.userDetailsExtras.detail.title">UserDetailsExtras</Translate> [
            <b>{userDetailsExtrasEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="numberOfPoints">
                <Translate contentKey="makamApp.userDetailsExtras.numberOfPoints">Number Of Points</Translate>
              </span>
            </dt>
            <dd>{userDetailsExtrasEntity.numberOfPoints}</dd>
            <dt>
              <span id="numberOfBeingLateInRow">
                <Translate contentKey="makamApp.userDetailsExtras.numberOfBeingLateInRow">Number Of Being Late In Row</Translate>
              </span>
            </dt>
            <dd>{userDetailsExtrasEntity.numberOfBeingLateInRow}</dd>
            <dt>
              <span id="numberOfCoursesFinished">
                <Translate contentKey="makamApp.userDetailsExtras.numberOfCoursesFinished">Number Of Courses Finished</Translate>
              </span>
            </dt>
            <dd>{userDetailsExtrasEntity.numberOfCoursesFinished}</dd>
            <dt>
              <span id="numberOfBeingLateTotal">
                <Translate contentKey="makamApp.userDetailsExtras.numberOfBeingLateTotal">Number Of Being Late Total</Translate>
              </span>
            </dt>
            <dd>{userDetailsExtrasEntity.numberOfBeingLateTotal}</dd>
            <dt>
              <span id="numberOfCoursesTotalOmited">
                <Translate contentKey="makamApp.userDetailsExtras.numberOfCoursesTotalOmited">Number Of Courses Total Omited</Translate>
              </span>
            </dt>
            <dd>{userDetailsExtrasEntity.numberOfCoursesTotalOmited}</dd>
            <dt>
              <span id="numberOfCoursesOmitedInRow">
                <Translate contentKey="makamApp.userDetailsExtras.numberOfCoursesOmitedInRow">Number Of Courses Omited In Row</Translate>
              </span>
            </dt>
            <dd>{userDetailsExtrasEntity.numberOfCoursesOmitedInRow}</dd>
          </dl>
          <Button tag={Link} to="/entity/user-details-extras" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/user-details-extras/${userDetailsExtrasEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ userDetailsExtras }: IRootState) => ({
  userDetailsExtrasEntity: userDetailsExtras.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(UserDetailsExtrasDetail);
