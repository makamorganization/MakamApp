import { ICertificate } from 'app/shared/model/certificate.model';
import { IAchievementDictionary } from 'app/shared/model/achievement-dictionary.model';

export interface IUserDetails {
  id?: number;
  studentCardNumber?: number;
  name?: string;
  surname?: string;
  telephoneNumber?: string;
  studyYear?: number;
  faculty?: string;
  fieldOfStudy?: string;
  userDetailsExtrasId?: number;
  certificates?: ICertificate[];
  achievementDictionaries?: IAchievementDictionary[];
  courseParticipantId?: number;
}

export const defaultValue: Readonly<IUserDetails> = {};
