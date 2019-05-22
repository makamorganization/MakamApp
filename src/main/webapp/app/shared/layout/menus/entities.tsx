import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  // tslint:disable-next-line:jsx-self-close
  <NavDropdown icon="th-list" name={translate('global.menu.entities.main')} id="entity-menu">
    <MenuItem icon="asterisk" to="/entity/course">
      <Translate contentKey="global.menu.entities.course" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/user-details">
      <Translate contentKey="global.menu.entities.userDetails" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/user-details-extras">
      <Translate contentKey="global.menu.entities.userDetailsExtras" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/course-participant">
      <Translate contentKey="global.menu.entities.courseParticipant" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/certificate">
      <Translate contentKey="global.menu.entities.certificate" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/achievement-dictionary">
      <Translate contentKey="global.menu.entities.achievementDictionary" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/faculty-dictionary">
      <Translate contentKey="global.menu.entities.facultyDictionary" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/field-of-study-dictionary">
      <Translate contentKey="global.menu.entities.fieldOfStudyDictionary" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
