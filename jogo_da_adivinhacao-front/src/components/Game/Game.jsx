import React from 'react';

import Display from '../Display/Display'
import { 
  BEGIN, 
  PLAYING, 
  END, 
  SUMMARY
} from '../../utils/constants'

import 'bootstrap/dist/css/bootstrap.min.css'
import './Game.css';

export default ( props ) => {

	const { 
		status,
		name,
		displayName,
		matchesPlayer,
		guess,
		setName,
		onStart, 
		onSame, 
		onBegin, 
		onLess, 
		onPlayAgain, 
		onFinish, 
		onBig } = props

	const centerButton1 = status === BEGIN ? 
		<button 
			type="button" className="btn btn-success btn-lg btn-block text-button" onClick={onStart}>
				Começar
		</button>: null   
	
	const centerButton2 = status === PLAYING ? 
		<button 
			type="button" className="btn btn-success btn-lg text-button2" onClick={onSame}>
				Igual
		</button>: null     

	const centerButton3 = status === END ? 
		<button 
			type="button" className="btn btn-primary btn-lg btn-block text-button" onClick={onBegin}>
				Início
		</button>: null 

	const leftButton1 = status === PLAYING ?
		<button type="button" className="btn btn-info btn-lg text-button2" onClick={onLess}>
			Menor
		</button> : null

	const leftButton2 = status === SUMMARY ?
		<button type="button" className="btn btn-info btn-lg text-button3" onClick={onPlayAgain}>
			Jogar novamente
		</button> : null

	const rightButton1 = status === PLAYING ?
		<button type="button" className="btn btn-info btn-lg text-button2" onClick={onBig}>
			Maior
		</button> : null

	const rightButton2 = status === SUMMARY ?
		<button type="button" className="btn btn-info btn-lg text-button3" onClick={onFinish}>
			Terminar
		</button> : null

	const input = (
		<div className="input-div">
			<input 
				type="text" className="form-control input" aria-label="Small" 
				aria-describedby="inputGroup-sizing-sm" placeholder="Nome do jogador" 
				value={name} onChange={setName}>
			</input>
		</div>  
	)
	const className1 = (status !== BEGIN && status !== END) ? "container container-buttons" : "container"
	const className2 = (status !== BEGIN && status !== END) ? "button" : ""
	return(
		<div>
			<Display 
				title={displayName} status={status} 
				text={guess} matches={matchesPlayer}>
			</Display>
			<div className={className1}>
				<div className="button">{leftButton1 || leftButton2}</div>
				{ status === BEGIN ? input : null }
				<div className={className2}>{centerButton1 || centerButton2 || centerButton3}</div>
				<div className="button">{rightButton1 || rightButton2}</div>
			</div>
		</div>
	)
}