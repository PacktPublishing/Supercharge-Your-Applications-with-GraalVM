function Book(props) {
    var bookname = props.bookname
    var bookdescription = props.description
    var imageLink = props.imageLink
    var author = ""
    if (props.author) {author = props.author.join()}
    return (
        <div className="Book">
            <div class="u-container-style u-list-item u-repeater-item u-video-cover u-white u-list-item-1">
                <div class="u-container-layout u-similar-container u-container-layout-1">
                    <img alt="" class="u-align-center u-image u-image-default u-image-1"
                        src={imageLink}/>
                        <div
                            class="u-align-center u-container-style u-expanded-width-lg u-expanded-width-md u-expanded-width-xl u-group u-video-cover u-group-1">
                            <div class="u-container-layout u-valign-top u-container-layout-2">
                                <h4 class="u-custom-font u-font-roboto-condensed u-text u-text-2">{bookname}</h4>
                                <h5 class="u-text u-text-palette-1-base u-text-2">{author}</h5>
                                <p class="u-text u-text-4">{bookdescription}</p>
                                    <a href="https://nicepage.com/wordpress-website-builder"
                                        class="u-border-2 u-border-grey-25 u-btn u-btn-rectangle u-button-style u-none u-text-body-color u-btn-1">Add to Library</a>
                            </div>
                        </div>
                </div>
            </div>
        </div>
  );
}

export default Book;