export interface ICourseParticipant {
  id?: number;
  isUserPresent?: boolean;
  isUserLate?: boolean;
  canCancelCourseAttendance?: boolean;
  courseId?: number;
  userId?: number;
}

export const defaultValue: Readonly<ICourseParticipant> = {
  isUserPresent: false,
  isUserLate: false,
  canCancelCourseAttendance: false
};
