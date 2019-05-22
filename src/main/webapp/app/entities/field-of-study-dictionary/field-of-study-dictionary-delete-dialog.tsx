import React from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IFieldOfStudyDictionary } from 'app/shared/model/field-of-study-dictionary.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './field-of-study-dictionary.reducer';

export interface IFieldOfStudyDictionaryDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class FieldOfStudyDictionaryDeleteDialog extends React.Component<IFieldOfStudyDictionaryDeleteDialogProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  confirmDelete = event => {
    this.props.deleteEntity(this.props.fieldOfStudyDictionaryEntity.id);
    this.handleClose(event);
  };

  handleClose = event => {
    event.stopPropagation();
    this.props.history.goBack();
  };

  render() {
    const { fieldOfStudyDictionaryEntity } = this.props;
    return (
      <Modal isOpen toggle={this.handleClose}>
        <ModalHeader toggle={this.handleClose}>
          <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
        </ModalHeader>
        <ModalBody id="makamApp.fieldOfStudyDictionary.delete.question">
          <Translate contentKey="makamApp.fieldOfStudyDictionary.delete.question" interpolate={{ id: fieldOfStudyDictionaryEntity.id }}>
            Are you sure you want to delete this FieldOfStudyDictionary?
          </Translate>
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={this.handleClose}>
            <FontAwesomeIcon icon="ban" />
            &nbsp;
            <Translate contentKey="entity.action.cancel">Cancel</Translate>
          </Button>
          <Button id="jhi-confirm-delete-fieldOfStudyDictionary" color="danger" onClick={this.confirmDelete}>
            <FontAwesomeIcon icon="trash" />
            &nbsp;
            <Translate contentKey="entity.action.delete">Delete</Translate>
          </Button>
        </ModalFooter>
      </Modal>
    );
  }
}

const mapStateToProps = ({ fieldOfStudyDictionary }: IRootState) => ({
  fieldOfStudyDictionaryEntity: fieldOfStudyDictionary.entity
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FieldOfStudyDictionaryDeleteDialog);
