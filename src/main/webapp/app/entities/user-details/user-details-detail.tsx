import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './user-details.reducer';
import { IUserDetails } from 'app/shared/model/user-details.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IUserDetailsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class UserDetailsDetail extends React.Component<IUserDetailsDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { userDetailsEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="makamApp.userDetails.detail.title">UserDetails</Translate> [<b>{userDetailsEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="studentCardNumber">
                <Translate contentKey="makamApp.userDetails.studentCardNumber">Student Card Number</Translate>
              </span>
            </dt>
            <dd>{userDetailsEntity.studentCardNumber}</dd>
            <dt>
              <span id="name">
                <Translate contentKey="makamApp.userDetails.name">Name</Translate>
              </span>
            </dt>
            <dd>{userDetailsEntity.name}</dd>
            <dt>
              <span id="surname">
                <Translate contentKey="makamApp.userDetails.surname">Surname</Translate>
              </span>
            </dt>
            <dd>{userDetailsEntity.surname}</dd>
            <dt>
              <span id="telephoneNumber">
                <Translate contentKey="makamApp.userDetails.telephoneNumber">Telephone Number</Translate>
              </span>
            </dt>
            <dd>{userDetailsEntity.telephoneNumber}</dd>
            <dt>
              <span id="studyYear">
                <Translate contentKey="makamApp.userDetails.studyYear">Study Year</Translate>
              </span>
            </dt>
            <dd>{userDetailsEntity.studyYear}</dd>
            <dt>
              <span id="faculty">
                <Translate contentKey="makamApp.userDetails.faculty">Faculty</Translate>
              </span>
            </dt>
            <dd>{userDetailsEntity.faculty}</dd>
            <dt>
              <span id="fieldOfStudy">
                <Translate contentKey="makamApp.userDetails.fieldOfStudy">Field Of Study</Translate>
              </span>
            </dt>
            <dd>{userDetailsEntity.fieldOfStudy}</dd>
            <dt>
              <Translate contentKey="makamApp.userDetails.userDetailsExtras">User Details Extras</Translate>
            </dt>
            <dd>{userDetailsEntity.userDetailsExtrasId ? userDetailsEntity.userDetailsExtrasId : ''}</dd>
            <dt>
              <Translate contentKey="makamApp.userDetails.achievementDictionary">Achievement Dictionary</Translate>
            </dt>
            <dd>
              {userDetailsEntity.achievementDictionaries
                ? userDetailsEntity.achievementDictionaries.map((val, i) => (
                    <span key={val.id}>
                      <a>{val.id}</a>
                      {i === userDetailsEntity.achievementDictionaries.length - 1 ? '' : ', '}
                    </span>
                  ))
                : null}
            </dd>
          </dl>
          <Button tag={Link} to="/entity/user-details" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/user-details/${userDetailsEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ userDetails }: IRootState) => ({
  userDetailsEntity: userDetails.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(UserDetailsDetail);
