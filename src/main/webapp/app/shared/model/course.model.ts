import { Moment } from 'moment';
import { ICourseParticipant } from 'app/shared/model/course-participant.model';

export interface ICourse {
  id?: number;
  title?: string;
  description?: string;
  courseStartDate?: Moment;
  courseEndDate?: Moment;
  registerStartDate?: Moment;
  registerEndDate?: Moment;
  duration?: number;
  maximumNumberOfParticipants?: number;
  minimalNumberOfParticipants?: number;
  lecturerName?: string;
  lecturerSurname?: string;
  pointPerCourse?: number;
  isVisibleInApp?: boolean;
  courseParticipants?: ICourseParticipant[];
  certificateId?: number;
}

export const defaultValue: Readonly<ICourse> = {
  isVisibleInApp: false
};
