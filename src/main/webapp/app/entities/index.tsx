import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Course from './course';
import UserDetails from './user-details';
import UserDetailsExtras from './user-details-extras';
import CourseParticipant from './course-participant';
import Certificate from './certificate';
import AchievementDictionary from './achievement-dictionary';
import FacultyDictionary from './faculty-dictionary';
import FieldOfStudyDictionary from './field-of-study-dictionary';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/course`} component={Course} />
      <ErrorBoundaryRoute path={`${match.url}/user-details`} component={UserDetails} />
      <ErrorBoundaryRoute path={`${match.url}/user-details-extras`} component={UserDetailsExtras} />
      <ErrorBoundaryRoute path={`${match.url}/course-participant`} component={CourseParticipant} />
      <ErrorBoundaryRoute path={`${match.url}/certificate`} component={Certificate} />
      <ErrorBoundaryRoute path={`${match.url}/achievement-dictionary`} component={AchievementDictionary} />
      <ErrorBoundaryRoute path={`${match.url}/faculty-dictionary`} component={FacultyDictionary} />
      <ErrorBoundaryRoute path={`${match.url}/field-of-study-dictionary`} component={FieldOfStudyDictionary} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
