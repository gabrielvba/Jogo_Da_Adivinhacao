import React, { Component } from 'react';
import Api from './Api'

import Navbar from './components/Navbar/Navbar'
import Game from './components/Game/Game'
import Ranking from './components/Ranking/Ranking'

import { 
  BEGIN, 
  PLAYING, 
  END, 
  SUMMARY,
  RANKING,
  GAME,
  RULES,
  DISPLAY_BEGING_NAME,
  DISPLAY_PLAYING_NAME,
  DISPLAY_CURRENT_MATCH,
  DISPLAY_PLAYER_MATCH,
  MAX_RANDOM_NUMBER,
  MIN_RANDOM_NUMBER 
} from './utils/constants'
import util from './utils/util'

import 'bootstrap/dist/css/bootstrap.min.css'
import './App.css';

const randomNumberBetween = (min, max) => Math.floor(Math.random() * (max - min + 1) + min);  
const validateNumber = (value) => {
  let validated = true

  if (isNaN(value)){
    validated = false
    alert('Esse campo precisa ser um número')
  }
  return validated
}

export default class App extends Component {

  constructor(props){
    super(props)

    this.state = {
      displayName: DISPLAY_BEGING_NAME,
      status: BEGIN,
      guess: RULES,
      max_number: MAX_RANDOM_NUMBER,
      min_number: MIN_RANDOM_NUMBER,
      steps: 0,
      matches: [],
      matchesPlayer: [],
      name: '',
      top: '',
      yourMatches: 5,
      matchNum: 0,
      nav: GAME
    }

    this.setName = this.setName.bind(this)
    this.onStart = this.onStart.bind(this)
    this.onSame = this.onSame.bind(this)
    this.onFinish = this.onFinish.bind(this)
    this.onLess = this.onLess.bind(this)
    this.onBig = this.onBig.bind(this)
    this.onBegin = this.onBegin.bind(this)
    this.setTop = this.setTop.bind(this)
    this.setYourMatches = this.setYourMatches.bind(this)
    this.getTop = this.getTop.bind(this)
    this.getYourMatches = this.getYourMatches.bind(this)
    this.onPlayAgain = this.onPlayAgain.bind(this)
    this.setNavGame = this.setNavGame.bind(this)
    this.setNavRanking = this.setNavRanking.bind(this)
    this.renderPage = this.renderPage.bind(this)
  }

  async getTop() {
    try {
      const { top } = this.state

      const validated = validateNumber(top)

      if (validated){
        const url = `jogos/rank?linesPerPage=${this.state.top}`;
        const rank = await Api.get(url);
    
        this.setState({
          matches: rank.data.content
        })
      }
    } catch (error) {
      alert(error)
    }

  }

  async getYourMatches() {
    try {
      const { name } = this.state

      const url = `jogos/meusJogos?name=${name}`
      
      const res = await Api.get(url)
      
      this.setState({
        matchesPlayer: res.data.content
      })
    } catch (error) {
      alert(error)
    }
  }

  setName(event) {
    this.setState({
      name: event.target.value
    })
  }

  setTop(event) {
    this.setState({
      top: event.target.value
    })
  }

  setYourMatches(event) {
    this.setState({
      yourMatches: event.target.value
    })
  }

  setNavGame() {
    this.setState({
      nav: GAME
    })
  }

  async setNavRanking() {
    await this.getTop()

    this.setState({
      nav: RANKING
    })
  }

  onPlayAgain() {
    const { name, matchNum } = this.state

    const max = MAX_RANDOM_NUMBER
    const min = MIN_RANDOM_NUMBER
    const randomNumber = randomNumberBetween(min, max)

    this.setState({
      status: PLAYING,
      displayName: DISPLAY_PLAYING_NAME,
      max_number: max,
      min_number: min,
      guess: randomNumber,
      steps: 1,
      start: new Date().getTime(),
      matchNum: matchNum + 1,
      name: util.normalizeName(name)
    })
  }

  async onStart() {
    try {
      const { name } = this.state
      const url = `/jogos/meusJogos?name=${name}`

      const retorno = await Api.get(url);
      const {content} = retorno.data;

      if (content.length > 0) {
        const { partidas } =  content[0];
        
        this.setState({
          matchNum: partidas
        })
      }
      this.onPlayAgain();
    } catch (error) {
      alert(error)
    }
  }

  onLess() {
    const min = this.state.min_number
    let max = this.state.guess
    if(max - min > 1) max -= 1;

    const randomNumber = randomNumberBetween(min, max)

    this.setState({
      guess: randomNumber,
      max_number: max,
      steps: (max === min ) ? this.state.steps : this.state.steps + 1
    })
  }

  onBig() {
    let min = this.state.guess
    const max = this.state.max_number
    if(max - min > 1) min += 1;
    const randomNumber = randomNumberBetween(min, max)

    this.setState({
      guess: randomNumber,
      min_number: min,
      steps: (max === min ) ? this.state.steps : this.state.steps + 1
    })
  }

  async onSame() {
    try {
      const end = new Date().getTime()

      const body = {
        nome: this.state.name,
        tentativas: this.state.steps,
        partidas: this.state.matchNum,
        tempoInicio: this.state.start,
        tempoFim: end
      }

      const match = await Api.post('/jogos', body)
      
      this.setState({
        displayName: DISPLAY_CURRENT_MATCH,
        status: SUMMARY,
        end,
        matchesPlayer: [match.data]
      })
    } catch (error) {
      alert(error)
    }
  }

  async onFinish() {
    await this.getYourMatches()

    this.setState({
      displayName: DISPLAY_PLAYER_MATCH,
      status: END
    })
  }

  onBegin() {
    try {
      this.setState({
        displayName: DISPLAY_BEGING_NAME,
        status: BEGIN,
        guess: RULES,
        matchesPlayer: [],
        name: '',
        top: '',
        yourMatches: 5,
        matchNum: 0
      })

    } catch (error) {
      alert(error)
    }
  }

  renderPage() {
    if ( this.state.nav === GAME ) {
      return (
        <Game status={this.state.status}
        name={this.state.name}
        displayName={this.state.displayName}
        matchesPlayer={this.state.matchesPlayer}
        guess={this.state.guess}
        setName={this.setName}
        onStart={this.onStart} 
        onSame={this.onSame} 
        onBegin={this.onBegin} 
        onLess={this.onLess} 
        onPlayAgain={this.onPlayAgain} 
        onFinish={this.onFinish} 
        onBig={this.onBig}
        ></Game>
      ) 
    }
    else {
      return (
        <Ranking
        top={this.state.top}
        matches={this.state.matches}
        getTop={this.getTop}
        setTop={this.setTop}
        ></Ranking>
      )
    }
  }
  
  render(){
    return(
      <div>
        <Navbar nav={this.state.nav} setNavRanking={this.setNavRanking} 
        setNavGame={this.setNavGame}></Navbar>
        <div>
          {this.renderPage()}
        </div>
      </div>
    )
  }
}