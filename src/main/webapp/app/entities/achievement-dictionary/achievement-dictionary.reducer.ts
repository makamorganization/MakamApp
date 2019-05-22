import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAchievementDictionary, defaultValue } from 'app/shared/model/achievement-dictionary.model';

export const ACTION_TYPES = {
  FETCH_ACHIEVEMENTDICTIONARY_LIST: 'achievementDictionary/FETCH_ACHIEVEMENTDICTIONARY_LIST',
  FETCH_ACHIEVEMENTDICTIONARY: 'achievementDictionary/FETCH_ACHIEVEMENTDICTIONARY',
  CREATE_ACHIEVEMENTDICTIONARY: 'achievementDictionary/CREATE_ACHIEVEMENTDICTIONARY',
  UPDATE_ACHIEVEMENTDICTIONARY: 'achievementDictionary/UPDATE_ACHIEVEMENTDICTIONARY',
  DELETE_ACHIEVEMENTDICTIONARY: 'achievementDictionary/DELETE_ACHIEVEMENTDICTIONARY',
  RESET: 'achievementDictionary/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAchievementDictionary>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type AchievementDictionaryState = Readonly<typeof initialState>;

// Reducer

export default (state: AchievementDictionaryState = initialState, action): AchievementDictionaryState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ACHIEVEMENTDICTIONARY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ACHIEVEMENTDICTIONARY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ACHIEVEMENTDICTIONARY):
    case REQUEST(ACTION_TYPES.UPDATE_ACHIEVEMENTDICTIONARY):
    case REQUEST(ACTION_TYPES.DELETE_ACHIEVEMENTDICTIONARY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ACHIEVEMENTDICTIONARY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ACHIEVEMENTDICTIONARY):
    case FAILURE(ACTION_TYPES.CREATE_ACHIEVEMENTDICTIONARY):
    case FAILURE(ACTION_TYPES.UPDATE_ACHIEVEMENTDICTIONARY):
    case FAILURE(ACTION_TYPES.DELETE_ACHIEVEMENTDICTIONARY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ACHIEVEMENTDICTIONARY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ACHIEVEMENTDICTIONARY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ACHIEVEMENTDICTIONARY):
    case SUCCESS(ACTION_TYPES.UPDATE_ACHIEVEMENTDICTIONARY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ACHIEVEMENTDICTIONARY):
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

const apiUrl = 'api/achievement-dictionaries';

// Actions

export const getEntities: ICrudGetAllAction<IAchievementDictionary> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ACHIEVEMENTDICTIONARY_LIST,
  payload: axios.get<IAchievementDictionary>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IAchievementDictionary> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ACHIEVEMENTDICTIONARY,
    payload: axios.get<IAchievementDictionary>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IAchievementDictionary> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ACHIEVEMENTDICTIONARY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAchievementDictionary> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ACHIEVEMENTDICTIONARY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAchievementDictionary> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ACHIEVEMENTDICTIONARY,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
