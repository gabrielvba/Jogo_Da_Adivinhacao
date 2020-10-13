import React from 'react'
import './Navbar.css'
import { GAME, RANKING } from '../../utils/constants'

export default props => {

  const { nav, setNavGame, setNavRanking } = props

  const classNameJ = (nav === GAME) ? 'nav-item click' : 'nav-item'
  const classNameR = (nav === RANKING) ? 'nav-item click' : 'nav-item'

  const aClassNameJ = (nav === GAME) ? 'nav-link b' : 'nav-link a'
  const aClassNameR = (nav === RANKING) ? 'nav-link b' : 'nav-link a'
  return(
    <div>
      <ul className="nav nav-pills nav-fill nav-menu">
        <li className={classNameJ} onClick={ () => setNavGame()}>
          <a className={aClassNameJ}>Jogo</a>
        </li>
        <li className={classNameR} onClick={ () => setNavRanking()}>
          <a className={aClassNameR}>Ranking</a>
        </li>
      </ul>
    </div>
  )
}
