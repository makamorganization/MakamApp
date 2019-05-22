import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IFacultyDictionary, defaultValue } from 'app/shared/model/faculty-dictionary.model';

export const ACTION_TYPES = {
  FETCH_FACULTYDICTIONARY_LIST: 'facultyDictionary/FETCH_FACULTYDICTIONARY_LIST',
  FETCH_FACULTYDICTIONARY: 'facultyDictionary/FETCH_FACULTYDICTIONARY',
  CREATE_FACULTYDICTIONARY: 'facultyDictionary/CREATE_FACULTYDICTIONARY',
  UPDATE_FACULTYDICTIONARY: 'facultyDictionary/UPDATE_FACULTYDICTIONARY',
  DELETE_FACULTYDICTIONARY: 'facultyDictionary/DELETE_FACULTYDICTIONARY',
  RESET: 'facultyDictionary/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IFacultyDictionary>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type FacultyDictionaryState = Readonly<typeof initialState>;

// Reducer

export default (state: FacultyDictionaryState = initialState, action): FacultyDictionaryState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_FACULTYDICTIONARY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_FACULTYDICTIONARY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_FACULTYDICTIONARY):
    case REQUEST(ACTION_TYPES.UPDATE_FACULTYDICTIONARY):
    case REQUEST(ACTION_TYPES.DELETE_FACULTYDICTIONARY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_FACULTYDICTIONARY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_FACULTYDICTIONARY):
    case FAILURE(ACTION_TYPES.CREATE_FACULTYDICTIONARY):
    case FAILURE(ACTION_TYPES.UPDATE_FACULTYDICTIONARY):
    case FAILURE(ACTION_TYPES.DELETE_FACULTYDICTIONARY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_FACULTYDICTIONARY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_FACULTYDICTIONARY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_FACULTYDICTIONARY):
    case SUCCESS(ACTION_TYPES.UPDATE_FACULTYDICTIONARY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_FACULTYDICTIONARY):
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

const apiUrl = 'api/faculty-dictionaries';

// Actions

export const getEntities: ICrudGetAllAction<IFacultyDictionary> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_FACULTYDICTIONARY_LIST,
  payload: axios.get<IFacultyDictionary>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IFacultyDictionary> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_FACULTYDICTIONARY,
    payload: axios.get<IFacultyDictionary>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IFacultyDictionary> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_FACULTYDICTIONARY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IFacultyDictionary> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_FACULTYDICTIONARY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IFacultyDictionary> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_FACULTYDICTIONARY,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
