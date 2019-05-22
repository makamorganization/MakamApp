import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import FieldOfStudyDictionary from './field-of-study-dictionary';
import FieldOfStudyDictionaryDetail from './field-of-study-dictionary-detail';
import FieldOfStudyDictionaryUpdate from './field-of-study-dictionary-update';
import FieldOfStudyDictionaryDeleteDialog from './field-of-study-dictionary-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FieldOfStudyDictionaryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FieldOfStudyDictionaryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FieldOfStudyDictionaryDetail} />
      <ErrorBoundaryRoute path={match.url} component={FieldOfStudyDictionary} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={FieldOfStudyDictionaryDeleteDialog} />
  </>
);

export default Routes;
