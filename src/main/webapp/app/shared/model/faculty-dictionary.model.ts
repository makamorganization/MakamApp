import { IFieldOfStudyDictionary } from 'app/shared/model/field-of-study-dictionary.model';

export interface IFacultyDictionary {
  id?: number;
  key?: string;
  value?: string;
  fieldOfStudies?: IFieldOfStudyDictionary[];
}

export const defaultValue: Readonly<IFacultyDictionary> = {};
