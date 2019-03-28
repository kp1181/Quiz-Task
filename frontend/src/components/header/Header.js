import React, { Component } from 'react';
import './Header.css';

export class Header extends Component {
	render() {
		return (
			<div>
				<header className="HeaderContent">
					<div className="HeaderAdd">
						<h4>Calculator</h4>
					</div>
				</header>
			</div>
		);
	}
}

export default Header;
