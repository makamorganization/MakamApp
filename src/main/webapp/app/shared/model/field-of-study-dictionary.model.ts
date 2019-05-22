export interface IFieldOfStudyDictionary {
  id?: number;
  key?: string;
  value?: string;
  facultyDictionaryId?: number;
}

export const defaultValue: Readonly<IFieldOfStudyDictionary> = {};
