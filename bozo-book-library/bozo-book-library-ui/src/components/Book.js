import React, { useContext } from 'react';

import Popup from 'reactjs-popup';
import { CurrentUserContext } from './CurrentUserContext';

import Button from '@material-ui/core/Button';
import Snackbar from '@material-ui/core/Snackbar';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';

function Book(props) {

    const [openSuccess, setOpenSuccess] = React.useState(false);
    const [openFailed, setOpenFailed] = React.useState(false);

    const openMessage = () => {
        setOpenSuccess(true);
    };

    const handleClose = (event, reason) => {
        if (reason === 'clickaway') {
            return;
        }
        setOpenSuccess(false);
    };

    const openError = () => {
        setOpenFailed(true);
    };

    const handleErrorClose = (event, reason) => {
        if (reason === 'clickaway') {
            return;
        }
        setOpenFailed(false);
    };


    var bookname = props.bookname
    var bookdescription = props.description
    var imageLink = props.imageLink
    var author = ""
    var bookId = props.bookId
    if (props.author) { author = props.author.join() }

    const { user } = useContext(CurrentUserContext);

    const addToLibrary = (value) => {
        const requestOptions = {
            method: 'POST',
        };
        var url = 'http://localhost:9090/bozolib/add/' + user + '/' + bookId;
        fetch(url, requestOptions)
            .then(response => {
                if (!response.ok) { throw Error(response.statusText); }
                return response.text();
            }).then(data => openMessage())
            .catch(error => openError())
    };

    return (
        <div class="u-container-style u-list-item u-repeater-item u-video-cover u-white u-list-item-1">
            <div class="u-container-layout u-similar-container u-container-layout-1">
                <img alt="" class="u-image-2" src={imageLink} />
                <div class="u-align-center u-container-style u-expanded-width-lg u-expanded-width-md u-expanded-width-xl u-group u-video-cover u-group-1">
                    <div class="u-container-layout u-valign-top u-container-layout-2">
                        <h4 class="u-custom-font u-font-roboto-condensed u-text u-text-2">{bookname}</h4>
                        <h5 class="u-text u-text-palette-1-base u-text-3">{author}</h5>
                        <p class="u-text u-text-4">{bookdescription}</p>

                        <button class="u-border-2 u-border-grey-25 u-btn u-btn-rectangle u-button-style u-none u-text-body-color u-btn-1" onClick={() => { addToLibrary(bookId) }}>Add to Library</button>

                        <Popup trigger={<button>More Details</button>} position="right center" closeOnDocumentClick>
                            <section className="login">
                                <div className="loginContainer">

                                    <div class="u-container-style u-list-item u-repeater-item u-video-cover u-white u-list-item-1">
                                        <div class="u-container-layout u-similar-container u-container-layout-1">
                                            <img alt="" class="u-image-2" src={imageLink} />
                                            <div class="u-align-center u-container-style u-expanded-width-lg u-expanded-width-md u-expanded-width-xl u-group u-video-cover u-group-1">
                                                <div class="u-container-layout u-valign-top u-container-layout-2">
                                                    <h4 class="u-custom-font u-font-roboto-condensed u-text u-text-2">{bookname}</h4>
                                                    <h5 class="u-text u-text-palette-1-base u-text-3">{author}</h5>
                                                    <p class="u-text u-text-4">{bookdescription}</p>

                                                    <button class="u-border-2 u-border-grey-25 u-btn u-btn-rectangle u-button-style u-none u-text-body-color u-btn-1">Close</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </section>
                        </Popup>
                    </div>
                </div>
            </div>

            <Snackbar
                anchorOrigin={{
                    vertical: 'bottom',
                    horizontal: 'left',
                }}
                open={openSuccess}
                autoHideDuration={3000}
                onClose={handleClose}
                message="Added the Book to Library"
                action={
                    <React.Fragment>
                        <IconButton size="small" aria-label="close" color="inherit" onClick={handleClose}>
                            <CloseIcon fontSize="small" />
                        </IconButton>
                    </React.Fragment>
                }
            />


            <Snackbar
                anchorOrigin={{
                    vertical: 'bottom',
                    horizontal: 'left',
                }}
                open={openFailed}
                autoHideDuration={3000}
                onClose={handleClose}
                message="! Problem Adding the Book to Library. Check Administrator"
                action={
                    <React.Fragment>
                        <IconButton size="small" aria-label="close" color="inherit" onClick={handleErrorClose}>
                            <CloseIcon fontSize="small" />
                        </IconButton>
                    </React.Fragment>
                }
            />

        </div>);
}

export default Book;