import React, { useContext } from 'react';

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
    var buttonText = "Add to Library"
    if (props.author) { author = props.author.join() }

    if (author == "") {author = "Not Available"}

    if (props.removeButton) { buttonText = "Remove from Library" }

    const { user } = useContext(CurrentUserContext);
    const BOOK_LIB_SERVICE_URL= process.env.REACT_APP_BOOK_LIB_SERVICE_URL + '/booklib'

    const addOrRemoveBook = (value) => {
        if (props.removeButton) { 
            const requestOptions = {
                method: 'POST',
            };
            var url =  BOOK_LIB_SERVICE_URL+'/delete/' + user + '/' + bookId;
            fetch(url, requestOptions)
                .then(response => {
                    if (!response.ok) { throw Error(response.statusText); }
                    return response.text();
                }).then(data => openMessage())
                .catch(error => openError())
        } else {
            const requestOptions = {
                method: 'POST',
            };
            var bookLibUrl = BOOK_LIB_SERVICE_URL+'/add/' + user + '/' + bookId;
            fetch(bookLibUrl, requestOptions)
                .then(response => {
                    if (!response.ok) { throw Error(response.statusText); }
                    return response.text();
                }).then(data => openMessage())
                .catch(error => openError())
        }

    };

    return (
        <div class="u-container-style u-list-item u-repeater-item u-video-cover u-white u-list-item-1">
            <div class="u-container-layout u-similar-container u-container-layout">

                <div class="u-align-center u-container-style u-expanded-width-lg u-expanded-width-md u-expanded-width-xl u-group u-video-cover u-group-1">
                    <div class="u-container-layout u-valign-top u-container-layout-2">
                        <img alt="./noimage.png" class="u-image-1" src={imageLink} />
                        <br/>
                        <h6 class="u-custom-font u-font-roboto-condensed u-text u-text-2">{bookname}</h6>
                        <h7 class="u-text u-text-palette-1-base u-text-4">{author}</h7>
                        <p class="u-text u-text-4">{bookdescription}</p>
                        <Button variant="contained" color="primary" onClick={() => { addOrRemoveBook(bookId) }}>{buttonText}</Button>
                        <br/>

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
                message="Problem Adding the Book to Library. Check Administrator"
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