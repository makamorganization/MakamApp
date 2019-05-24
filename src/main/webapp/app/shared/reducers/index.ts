import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
// prettier-ignore
import course, {
  CourseState
} from 'app/entities/course/course.reducer';
// prettier-ignore
import userDetails, {
  UserDetailsState
} from 'app/entities/user-details/user-details.reducer';
// prettier-ignore
import userDetailsExtras, {
  UserDetailsExtrasState
} from 'app/entities/user-details-extras/user-details-extras.reducer';
// prettier-ignore
import courseParticipant, {
  CourseParticipantState
} from 'app/entities/course-participant/course-participant.reducer';
// prettier-ignore
import certificate, {
  CertificateState
} from 'app/entities/certificate/certificate.reducer';
// prettier-ignore
import achievementDictionary, {
  AchievementDictionaryState
} from 'app/entities/achievement-dictionary/achievement-dictionary.reducer';
// prettier-ignore
import facultyDictionary, {
  FacultyDictionaryState
} from 'app/entities/faculty-dictionary/faculty-dictionary.reducer';
// prettier-ignore
import fieldOfStudyDictionary, {
  FieldOfStudyDictionaryState
} from 'app/entities/field-of-study-dictionary/field-of-study-dictionary.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly activate: ActivateState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly course: CourseState;
  readonly userDetails: UserDetailsState;
  readonly userDetailsExtras: UserDetailsExtrasState;
  readonly courseParticipant: CourseParticipantState;
  readonly certificate: CertificateState;
  readonly achievementDictionary: AchievementDictionaryState;
  readonly facultyDictionary: FacultyDictionaryState;
  readonly fieldOfStudyDictionary: FieldOfStudyDictionaryState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  activate,
  password,
  settings,
  course,
  userDetails,
  userDetailsExtras,
  courseParticipant,
  certificate,
  achievementDictionary,
  facultyDictionary,
  fieldOfStudyDictionary,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
