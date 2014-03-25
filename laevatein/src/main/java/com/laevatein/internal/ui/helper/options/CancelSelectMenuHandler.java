/*
 * Copyright (C) 2014 nohana, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an &quot;AS IS&quot; BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.laevatein.internal.ui.helper.options;

import com.laevatein.internal.misc.ui.ConfirmationDialogFragment;
import com.laevatein.internal.ui.PhotoSelectionActivity;

/**
 * @author keishin.yokomaku
 * @since 2014/03/25
 */
public class CancelSelectMenuHandler implements PhotoSelectionOptionsMenuHandler {
    @Override
    public boolean handle(PhotoSelectionActivity activity, Void extra) {
        ConfirmationDialogFragment dialog = ConfirmationDialogFragment.newInstance(0, 0);
        dialog.show(activity.getSupportFragmentManager(), ConfirmationDialogFragment.TAG);
        return true;
    }
}
