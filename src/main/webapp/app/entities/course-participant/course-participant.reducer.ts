import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICourseParticipant, defaultValue } from 'app/shared/model/course-participant.model';

export const ACTION_TYPES = {
  FETCH_COURSEPARTICIPANT_LIST: 'courseParticipant/FETCH_COURSEPARTICIPANT_LIST',
  FETCH_COURSEPARTICIPANT: 'courseParticipant/FETCH_COURSEPARTICIPANT',
  CREATE_COURSEPARTICIPANT: 'courseParticipant/CREATE_COURSEPARTICIPANT',
  UPDATE_COURSEPARTICIPANT: 'courseParticipant/UPDATE_COURSEPARTICIPANT',
  DELETE_COURSEPARTICIPANT: 'courseParticipant/DELETE_COURSEPARTICIPANT',
  RESET: 'courseParticipant/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICourseParticipant>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type CourseParticipantState = Readonly<typeof initialState>;

// Reducer

export default (state: CourseParticipantState = initialState, action): CourseParticipantState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_COURSEPARTICIPANT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_COURSEPARTICIPANT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_COURSEPARTICIPANT):
    case REQUEST(ACTION_TYPES.UPDATE_COURSEPARTICIPANT):
    case REQUEST(ACTION_TYPES.DELETE_COURSEPARTICIPANT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_COURSEPARTICIPANT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_COURSEPARTICIPANT):
    case FAILURE(ACTION_TYPES.CREATE_COURSEPARTICIPANT):
    case FAILURE(ACTION_TYPES.UPDATE_COURSEPARTICIPANT):
    case FAILURE(ACTION_TYPES.DELETE_COURSEPARTICIPANT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_COURSEPARTICIPANT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_COURSEPARTICIPANT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_COURSEPARTICIPANT):
    case SUCCESS(ACTION_TYPES.UPDATE_COURSEPARTICIPANT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_COURSEPARTICIPANT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/course-participants';

// Actions

export const getEntities: ICrudGetAllAction<ICourseParticipant> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_COURSEPARTICIPANT_LIST,
  payload: axios.get<ICourseParticipant>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ICourseParticipant> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_COURSEPARTICIPANT,
    payload: axios.get<ICourseParticipant>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICourseParticipant> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_COURSEPARTICIPANT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICourseParticipant> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_COURSEPARTICIPANT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICourseParticipant> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_COURSEPARTICIPANT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
