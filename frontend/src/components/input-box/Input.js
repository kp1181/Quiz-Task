import React, { Component } from 'react';
import './Input.css';

export class Input extends Component {
	render() {
		return (
			<div class="row">
				<form class="col s12">
					<div class="row">
						<div class="input-field col s20">
							<input id="input_text" type="text" data-length="20" />
							<label for="input_text">Enter string</label>
							<input type="submit" />
							<button id="historyButton">History</button>
						</div>
					</div>
				</form>
			</div>
		);
	}
}

export default Input;
