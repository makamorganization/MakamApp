import './home.scss';

import React from 'react';
import { Translate } from 'react-jhipster';
import { connect } from 'react-redux';
import { Alert, Col, Row } from 'reactstrap';
import { getSession } from 'app/shared/reducers/authentication';

import styled from 'styled-components';

export interface IHomeProp extends StateProps, DispatchProps {}

export class Home extends React.Component<IHomeProp> {
  componentDidMount() {
    this.props.getSession();
  }

  redirectToGooglePlay = () => {
    window.location.assign('https://play.google.com/store');
  };

  render() {
    const { account } = this.props;
    return (
      <Row>
        <Col md="9">
          <h2 style={{ textAlign: 'center' }}>
            <Translate contentKey="home.title">Welcome!</Translate>
          </h2>
          {account && account.login ? (
            <div>
              <Alert color="success">
                <Translate contentKey="home.logged.message" interpolate={{ username: account.login }}>
                  You are logged in as user {account.login}.
                </Translate>
              </Alert>
            </div>
          ) : (
            <div>
              <div className="">
                <h4 style={{ textAlign: 'center', marginTop: '5vh' }}>
                  <Translate contentKey="home.subtitle">
                    If you want to enjoy the facilities offered by Makam, download the application.
                  </Translate>
                </h4>
                <ImageContainer className="col-md-12">
                  {/* https://www.iconfinder.com/icons/132832/google_play_icon */}
                  <img
                    src={require('../../../static/images/google_play_icon.png')}
                    width="150"
                    height="150"
                    className="mx-auto d-block"
                    alt="Google play store"
                    onClick={this.redirectToGooglePlay}
                  />
                </ImageContainer>
              </div>
            </div>
          )}
        </Col>
        <Col md="3" className="pad">
          <span className="hipster rounded" />
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = storeState => ({
  account: storeState.authentication.account,
  isAuthenticated: storeState.authentication.isAuthenticated
});

const mapDispatchToProps = { getSession };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Home);

const ImageContainer = styled.div`
  margin-top: 5vh;
  img {
    cursor: pointer;
  }
`;
