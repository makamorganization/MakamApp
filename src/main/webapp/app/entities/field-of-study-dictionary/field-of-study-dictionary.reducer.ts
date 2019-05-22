import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IFieldOfStudyDictionary, defaultValue } from 'app/shared/model/field-of-study-dictionary.model';

export const ACTION_TYPES = {
  FETCH_FIELDOFSTUDYDICTIONARY_LIST: 'fieldOfStudyDictionary/FETCH_FIELDOFSTUDYDICTIONARY_LIST',
  FETCH_FIELDOFSTUDYDICTIONARY: 'fieldOfStudyDictionary/FETCH_FIELDOFSTUDYDICTIONARY',
  CREATE_FIELDOFSTUDYDICTIONARY: 'fieldOfStudyDictionary/CREATE_FIELDOFSTUDYDICTIONARY',
  UPDATE_FIELDOFSTUDYDICTIONARY: 'fieldOfStudyDictionary/UPDATE_FIELDOFSTUDYDICTIONARY',
  DELETE_FIELDOFSTUDYDICTIONARY: 'fieldOfStudyDictionary/DELETE_FIELDOFSTUDYDICTIONARY',
  RESET: 'fieldOfStudyDictionary/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IFieldOfStudyDictionary>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type FieldOfStudyDictionaryState = Readonly<typeof initialState>;

// Reducer

export default (state: FieldOfStudyDictionaryState = initialState, action): FieldOfStudyDictionaryState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_FIELDOFSTUDYDICTIONARY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_FIELDOFSTUDYDICTIONARY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_FIELDOFSTUDYDICTIONARY):
    case REQUEST(ACTION_TYPES.UPDATE_FIELDOFSTUDYDICTIONARY):
    case REQUEST(ACTION_TYPES.DELETE_FIELDOFSTUDYDICTIONARY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_FIELDOFSTUDYDICTIONARY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_FIELDOFSTUDYDICTIONARY):
    case FAILURE(ACTION_TYPES.CREATE_FIELDOFSTUDYDICTIONARY):
    case FAILURE(ACTION_TYPES.UPDATE_FIELDOFSTUDYDICTIONARY):
    case FAILURE(ACTION_TYPES.DELETE_FIELDOFSTUDYDICTIONARY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_FIELDOFSTUDYDICTIONARY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_FIELDOFSTUDYDICTIONARY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_FIELDOFSTUDYDICTIONARY):
    case SUCCESS(ACTION_TYPES.UPDATE_FIELDOFSTUDYDICTIONARY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_FIELDOFSTUDYDICTIONARY):
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

const apiUrl = 'api/field-of-study-dictionaries';

// Actions

export const getEntities: ICrudGetAllAction<IFieldOfStudyDictionary> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_FIELDOFSTUDYDICTIONARY_LIST,
  payload: axios.get<IFieldOfStudyDictionary>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IFieldOfStudyDictionary> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_FIELDOFSTUDYDICTIONARY,
    payload: axios.get<IFieldOfStudyDictionary>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IFieldOfStudyDictionary> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_FIELDOFSTUDYDICTIONARY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IFieldOfStudyDictionary> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_FIELDOFSTUDYDICTIONARY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IFieldOfStudyDictionary> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_FIELDOFSTUDYDICTIONARY,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
