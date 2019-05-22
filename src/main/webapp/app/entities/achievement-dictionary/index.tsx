import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AchievementDictionary from './achievement-dictionary';
import AchievementDictionaryDetail from './achievement-dictionary-detail';
import AchievementDictionaryUpdate from './achievement-dictionary-update';
import AchievementDictionaryDeleteDialog from './achievement-dictionary-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AchievementDictionaryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AchievementDictionaryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AchievementDictionaryDetail} />
      <ErrorBoundaryRoute path={match.url} component={AchievementDictionary} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={AchievementDictionaryDeleteDialog} />
  </>
);

export default Routes;
