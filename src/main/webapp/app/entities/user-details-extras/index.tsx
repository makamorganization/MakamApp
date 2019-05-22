import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import UserDetailsExtras from './user-details-extras';
import UserDetailsExtrasDetail from './user-details-extras-detail';
import UserDetailsExtrasUpdate from './user-details-extras-update';
import UserDetailsExtrasDeleteDialog from './user-details-extras-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={UserDetailsExtrasUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={UserDetailsExtrasUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={UserDetailsExtrasDetail} />
      <ErrorBoundaryRoute path={match.url} component={UserDetailsExtras} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={UserDetailsExtrasDeleteDialog} />
  </>
);

export default Routes;
