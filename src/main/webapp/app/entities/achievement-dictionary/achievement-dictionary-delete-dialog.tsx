import React from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IAchievementDictionary } from 'app/shared/model/achievement-dictionary.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './achievement-dictionary.reducer';

export interface IAchievementDictionaryDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class AchievementDictionaryDeleteDialog extends React.Component<IAchievementDictionaryDeleteDialogProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  confirmDelete = event => {
    this.props.deleteEntity(this.props.achievementDictionaryEntity.id);
    this.handleClose(event);
  };

  handleClose = event => {
    event.stopPropagation();
    this.props.history.goBack();
  };

  render() {
    const { achievementDictionaryEntity } = this.props;
    return (
      <Modal isOpen toggle={this.handleClose}>
        <ModalHeader toggle={this.handleClose}>
          <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
        </ModalHeader>
        <ModalBody id="makamApp.achievementDictionary.delete.question">
          <Translate contentKey="makamApp.achievementDictionary.delete.question" interpolate={{ id: achievementDictionaryEntity.id }}>
            Are you sure you want to delete this AchievementDictionary?
          </Translate>
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={this.handleClose}>
            <FontAwesomeIcon icon="ban" />
            &nbsp;
            <Translate contentKey="entity.action.cancel">Cancel</Translate>
          </Button>
          <Button id="jhi-confirm-delete-achievementDictionary" color="danger" onClick={this.confirmDelete}>
            <FontAwesomeIcon icon="trash" />
            &nbsp;
            <Translate contentKey="entity.action.delete">Delete</Translate>
          </Button>
        </ModalFooter>
      </Modal>
    );
  }
}

const mapStateToProps = ({ achievementDictionary }: IRootState) => ({
  achievementDictionaryEntity: achievementDictionary.entity
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AchievementDictionaryDeleteDialog);
