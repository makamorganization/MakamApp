import { Moment } from 'moment';

export interface ICertificate {
  id?: number;
  title?: string;
  path?: string;
  validityEndDate?: Moment;
  signatureContentType?: string;
  signature?: any;
  userDetailsId?: number;
  courseId?: number;
}

export const defaultValue: Readonly<ICertificate> = {};
