import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import FacultyDictionary from './faculty-dictionary';
import FacultyDictionaryDetail from './faculty-dictionary-detail';
import FacultyDictionaryUpdate from './faculty-dictionary-update';
import FacultyDictionaryDeleteDialog from './faculty-dictionary-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FacultyDictionaryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FacultyDictionaryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FacultyDictionaryDetail} />
      <ErrorBoundaryRoute path={match.url} component={FacultyDictionary} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={FacultyDictionaryDeleteDialog} />
  </>
);

export default Routes;
