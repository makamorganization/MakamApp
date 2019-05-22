export interface IUserDetailsExtras {
  id?: number;
  numberOfPoints?: number;
  numberOfBeingLateInRow?: number;
  numberOfCoursesFinished?: number;
  numberOfBeingLateTotal?: number;
  numberOfCoursesTotalOmited?: number;
  numberOfCoursesOmitedInRow?: number;
  userDetailsId?: number;
}

export const defaultValue: Readonly<IUserDetailsExtras> = {};
