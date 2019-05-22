import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IUserDetailsExtras, defaultValue } from 'app/shared/model/user-details-extras.model';

export const ACTION_TYPES = {
  FETCH_USERDETAILSEXTRAS_LIST: 'userDetailsExtras/FETCH_USERDETAILSEXTRAS_LIST',
  FETCH_USERDETAILSEXTRAS: 'userDetailsExtras/FETCH_USERDETAILSEXTRAS',
  CREATE_USERDETAILSEXTRAS: 'userDetailsExtras/CREATE_USERDETAILSEXTRAS',
  UPDATE_USERDETAILSEXTRAS: 'userDetailsExtras/UPDATE_USERDETAILSEXTRAS',
  DELETE_USERDETAILSEXTRAS: 'userDetailsExtras/DELETE_USERDETAILSEXTRAS',
  RESET: 'userDetailsExtras/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IUserDetailsExtras>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type UserDetailsExtrasState = Readonly<typeof initialState>;

// Reducer

export default (state: UserDetailsExtrasState = initialState, action): UserDetailsExtrasState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_USERDETAILSEXTRAS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_USERDETAILSEXTRAS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_USERDETAILSEXTRAS):
    case REQUEST(ACTION_TYPES.UPDATE_USERDETAILSEXTRAS):
    case REQUEST(ACTION_TYPES.DELETE_USERDETAILSEXTRAS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_USERDETAILSEXTRAS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_USERDETAILSEXTRAS):
    case FAILURE(ACTION_TYPES.CREATE_USERDETAILSEXTRAS):
    case FAILURE(ACTION_TYPES.UPDATE_USERDETAILSEXTRAS):
    case FAILURE(ACTION_TYPES.DELETE_USERDETAILSEXTRAS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_USERDETAILSEXTRAS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_USERDETAILSEXTRAS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_USERDETAILSEXTRAS):
    case SUCCESS(ACTION_TYPES.UPDATE_USERDETAILSEXTRAS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_USERDETAILSEXTRAS):
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

const apiUrl = 'api/user-details-extras';

// Actions

export const getEntities: ICrudGetAllAction<IUserDetailsExtras> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_USERDETAILSEXTRAS_LIST,
  payload: axios.get<IUserDetailsExtras>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IUserDetailsExtras> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_USERDETAILSEXTRAS,
    payload: axios.get<IUserDetailsExtras>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IUserDetailsExtras> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_USERDETAILSEXTRAS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IUserDetailsExtras> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_USERDETAILSEXTRAS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IUserDetailsExtras> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_USERDETAILSEXTRAS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
