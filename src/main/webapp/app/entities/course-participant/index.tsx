import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CourseParticipant from './course-participant';
import CourseParticipantDetail from './course-participant-detail';
import CourseParticipantUpdate from './course-participant-update';
import CourseParticipantDeleteDialog from './course-participant-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CourseParticipantUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CourseParticipantUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CourseParticipantDetail} />
      <ErrorBoundaryRoute path={match.url} component={CourseParticipant} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={CourseParticipantDeleteDialog} />
  </>
);

export default Routes;
