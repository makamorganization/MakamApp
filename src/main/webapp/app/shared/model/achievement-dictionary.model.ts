import { IUserDetails } from 'app/shared/model/user-details.model';

export interface IAchievementDictionary {
  id?: number;
  key?: string;
  value?: string;
  enabled?: boolean;
  description?: string;
  userDetails?: IUserDetails[];
}

export const defaultValue: Readonly<IAchievementDictionary> = {
  enabled: false
};
