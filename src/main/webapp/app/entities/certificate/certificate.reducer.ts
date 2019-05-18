import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICertificate, defaultValue } from 'app/shared/model/certificate.model';

export const ACTION_TYPES = {
  FETCH_CERTIFICATE_LIST: 'certificate/FETCH_CERTIFICATE_LIST',
  FETCH_CERTIFICATE: 'certificate/FETCH_CERTIFICATE',
  CREATE_CERTIFICATE: 'certificate/CREATE_CERTIFICATE',
  UPDATE_CERTIFICATE: 'certificate/UPDATE_CERTIFICATE',
  DELETE_CERTIFICATE: 'certificate/DELETE_CERTIFICATE',
  SET_BLOB: 'certificate/SET_BLOB',
  RESET: 'certificate/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICertificate>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type CertificateState = Readonly<typeof initialState>;

// Reducer

export default (state: CertificateState = initialState, action): CertificateState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CERTIFICATE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CERTIFICATE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CERTIFICATE):
    case REQUEST(ACTION_TYPES.UPDATE_CERTIFICATE):
    case REQUEST(ACTION_TYPES.DELETE_CERTIFICATE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CERTIFICATE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CERTIFICATE):
    case FAILURE(ACTION_TYPES.CREATE_CERTIFICATE):
    case FAILURE(ACTION_TYPES.UPDATE_CERTIFICATE):
    case FAILURE(ACTION_TYPES.DELETE_CERTIFICATE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CERTIFICATE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_CERTIFICATE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CERTIFICATE):
    case SUCCESS(ACTION_TYPES.UPDATE_CERTIFICATE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CERTIFICATE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.SET_BLOB:
      const { name, data, contentType } = action.payload;
      return {
        ...state,
        entity: {
          ...state.entity,
          [name]: data,
          [name + 'ContentType']: contentType
        }
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/certificates';

// Actions

export const getEntities: ICrudGetAllAction<ICertificate> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CERTIFICATE_LIST,
  payload: axios.get<ICertificate>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ICertificate> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CERTIFICATE,
    payload: axios.get<ICertificate>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICertificate> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CERTIFICATE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICertificate> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CERTIFICATE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICertificate> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CERTIFICATE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const setBlob = (name, data, contentType?) => ({
  type: ACTION_TYPES.SET_BLOB,
  payload: {
    name,
    data,
    contentType
  }
});

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
