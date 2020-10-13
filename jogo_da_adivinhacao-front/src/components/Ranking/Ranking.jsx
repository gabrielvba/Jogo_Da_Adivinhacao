import React, { Fragment } from 'react';

import Display from '../Display/Display'
import { 
  RANKING,
  DISPLAY_RANKING_NAME,
} from '../../utils/constants'

import 'bootstrap/dist/css/bootstrap.min.css'
import './Ranking.css';

export default ( props ) => {

	const { 
    top,
    getTop,
    setTop,
    matches
} = props

  return (
    <Fragment>
      <div className="container container-buttons">
        <input 
          type="text" className="form-control input top" aria-label="Small" 
          aria-describedby="inputGroup-sizing-sm" placeholder={`Tamanho do ranking`} 
          value={top} onChange={setTop}>
        </input>
        <button 
          type="button" className="btn btn-outline-dark" onClick={getTop}
        >Get</button>
      </div>
        <Display 
          matches={matches} title={DISPLAY_RANKING_NAME} status={RANKING}>
        </Display>
    </Fragment>
  )
}