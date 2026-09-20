package com.dialogshowcase;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.dialogshowcase.dialogs.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
	
	private CoordinatorLayout coordinator;
	private AppBarLayout app_bar;
	private FrameLayout frame2;
	private LinearLayout layout_collapsible_header;
	private LinearLayout linear1;
	private View gap1;
	private LinearLayout linear2;
	private TextView textview2;
	private LinearLayout cardGithubSection;
	private TextView textview1;
	private TextView tvTotalCountBadge;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private FrameLayout frame1;
	private LinearLayout linear5;
	private ImageView imageview1;
	private TextView textview3;
	private TextView textview4;
	private MaterialButton btnHomeGithubFollow;
	private MaterialButton btnHomeGithubRepo;
	private LinearLayout linear6;
	private HorizontalScrollView hscroll1;
	private ImageView imageview2;
	private EditText etSearch;
	private ImageView btnClearSearch;
	private ChipGroup chipGroupCategories;
	private Chip chip_all;
	private Chip chip_sheets;
	private Chip chip_wizards;
	private Chip chip_animations;
	private Chip chip_sweet;
	private Chip chip_material;
	private Chip chip_inputs;
	private Chip chip_progress;
	private Chip chip_custom;
	private RecyclerView recyclerDialogs;
	private LinearLayout layoutEmptyState;
	private ImageView imageview4;
	private TextView textview5;
	private TextView textview6;
	private MaterialButton btnResetSearch;
    private MaterialButton materialbutton1;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		if (!isTaskRoot()) {
			final Intent intent = getIntent();
			final String intentAction = intent.getAction();
			if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && intentAction != null && intentAction.equals(Intent.ACTION_MAIN)) {
				finish();
				return;
			}
		}
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		coordinator = findViewById(R.id.coordinator);
		app_bar = findViewById(R.id.app_bar);
		frame2 = findViewById(R.id.frame2);
		layout_collapsible_header = findViewById(R.id.layout_collapsible_header);
		linear1 = findViewById(R.id.linear1);
		gap1 = findViewById(R.id.gap1);
		linear2 = findViewById(R.id.linear2);
		textview2 = findViewById(R.id.textview2);
		cardGithubSection = findViewById(R.id.cardGithubSection);
		textview1 = findViewById(R.id.textview1);
		tvTotalCountBadge = findViewById(R.id.tvTotalCountBadge);
		linear3 = findViewById(R.id.linear3);
		linear4 = findViewById(R.id.linear4);
		frame1 = findViewById(R.id.frame1);
		linear5 = findViewById(R.id.linear5);
		imageview1 = findViewById(R.id.imageview1);
		textview3 = findViewById(R.id.textview3);
		textview4 = findViewById(R.id.textview4);
		btnHomeGithubFollow = findViewById(R.id.btnHomeGithubFollow);
		btnHomeGithubRepo = findViewById(R.id.btnHomeGithubRepo);
		linear6 = findViewById(R.id.linear6);
		hscroll1 = findViewById(R.id.hscroll1);
		imageview2 = findViewById(R.id.imageview2);
		etSearch = findViewById(R.id.etSearch);
		btnClearSearch = findViewById(R.id.btnClearSearch);
		chipGroupCategories = findViewById(R.id.chipGroupCategories);
		chip_all = findViewById(R.id.chip_all);
		chip_sheets = findViewById(R.id.chip_sheets);
		chip_wizards = findViewById(R.id.chip_wizards);
		chip_animations = findViewById(R.id.chip_animations);
		chip_sweet = findViewById(R.id.chip_sweet);
		chip_material = findViewById(R.id.chip_material);
		chip_inputs = findViewById(R.id.chip_inputs);
		chip_progress = findViewById(R.id.chip_progress);
		chip_custom = findViewById(R.id.chip_custom);
		recyclerDialogs = findViewById(R.id.recyclerDialogs);
		layoutEmptyState = findViewById(R.id.layoutEmptyState);
		imageview4 = findViewById(R.id.imageview4);
		textview5 = findViewById(R.id.textview5);
		textview6 = findViewById(R.id.textview6);
		btnResetSearch = findViewById(R.id.btnResetSearch);
        materialbutton1 = findViewById(R.id.materialbutton1);
        
        materialbutton1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View _view) {

            Intent intent = new Intent(MainActivity.this, BestfiveActivity.class);
            startActivity(intent);
            overridePendingTransition(
            R.anim.activity_in,
            R.anim.activity_out
            );
 
        } });
	}
	
	private void initializeLogic() {
		_setupRecyclerView();
	}
	
	
	public static final String GITHUB_PROFILE_URL = "https://github.com/yodharaja";
	public static final String GITHUB_PROJECT_URL = "https://github.com/yodharaja/Dialog-Library";
	private DialogAdapter adapter;
	private String currentCategory = "All";
	
	private void _setupGitHubSection() {
		btnHomeGithubFollow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				openBrowser(GITHUB_PROFILE_URL);
			}
		});
		
		btnHomeGithubRepo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				openBrowser(GITHUB_PROJECT_URL);
			}
		});
		
		cardGithubSection.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				GitHubCommunityDialog.show(MainActivity.this, GITHUB_PROFILE_URL, GITHUB_PROJECT_URL);
			}
		});
		
	}
	
	public void _setupRecyclerView() {
		recyclerDialogs.setLayoutManager(new LinearLayoutManager(this));
		List<DialogItem> items = createDialogItems();
		if (tvTotalCountBadge != null) {
			tvTotalCountBadge.setText(items.size() + " Dialogs"
			);
		}
		adapter = new DialogAdapter(this, items);
		adapter.setCountListener(new DialogAdapter.OnFilteredCountChangedListener() {
			@Override
			public void onCountChanged(int count) {
				if (count == 0) {
					layoutEmptyState.setVisibility(View.VISIBLE);
					recyclerDialogs.setVisibility(View.GONE);
				} else {
					layoutEmptyState.setVisibility(View.GONE);
					recyclerDialogs.setVisibility(View.VISIBLE);
				}
			}
		});
		recyclerDialogs.setAdapter(adapter);
	}
	
	public class RecyclerDialogsAdapter extends RecyclerView.Adapter<RecyclerDialogsAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public RecyclerDialogsAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.item_dialog_card, parent, false);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			// Fix: Remove extra spaces caused by Sketchware's match_parent custom view
			ViewGroup.LayoutParams _lp = _view.getLayoutParams();
			if (_lp != null) {
				_lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
				_view.setLayoutParams(_lp);
			} else {
				_view.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			}

			final View baseView = _view.findViewById(R.id.base);
			final LinearLayout base = baseView instanceof LinearLayout ? (LinearLayout) baseView : (LinearLayout) _view;
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final TextView card_description = _view.findViewById(R.id.card_description);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final FrameLayout card_icon_container = _view.findViewById(R.id.card_icon_container);
			final ImageView card_icon = _view.findViewById(R.id.card_icon);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final TextView card_category = _view.findViewById(R.id.card_category);
			final TextView card_title = _view.findViewById(R.id.card_title);
			final com.google.android.material.button.MaterialButton card_btn_code = _view.findViewById(R.id.card_btn_code);
			final com.google.android.material.button.MaterialButton card_btn_launch = _view.findViewById(R.id.card_btn_launch);
			
			final HashMap<String, Object> _item = _data.get(_position);
			if (_item != null) {
				if (card_title != null && _item.containsKey("title")) {
					card_title.setText(String.valueOf(_item.get("title")));
				}
				if (card_description != null && _item.containsKey("desc")) {
					card_description.setText(String.valueOf(_item.get("desc")));
				}
				if (card_category != null && _item.containsKey("category")) {
					card_category.setText(String.valueOf(_item.get("category")));
				}
				if (card_icon != null && _item.containsKey("icon")) {
					card_icon.setImageResource((Integer) _item.get("icon"));
				}
				
				final DialogItem.DialogLauncher launcher = _item.get("launcher") instanceof DialogItem.DialogLauncher 
						? (DialogItem.DialogLauncher) _item.get("launcher") 
						: null;
				
				if (card_btn_launch != null) {
					card_btn_launch.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _btnView) {
							if (launcher != null) {
								launcher.launch(MainActivity.this);
							}
						}
					});
				}
				
				View.OnClickListener cardClickListener = new View.OnClickListener() {
					@Override
					public void onClick(View _cardView) {
						if (launcher != null) {
							launcher.launch(MainActivity.this);
						}
					}
				};

				if (base != null) {
					base.setOnClickListener(cardClickListener);
				}
				_view.setOnClickListener(cardClickListener);
				
				if (card_btn_code != null) {
					card_btn_code.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _codeView) {
							CodeSnippetDialog.show(MainActivity.this,
									_item.get("title") != null ? _item.get("title").toString() : "",
									_item.get("java_file") != null ? _item.get("java_file").toString() : "",
									_item.get("layout_xml") != null ? _item.get("layout_xml").toString() : "",
									_item.get("drawables") != null ? _item.get("drawables").toString() : "",
									_item.get("anim_xml") != null ? _item.get("anim_xml").toString() : "",
									_item.get("code_snippet") != null ? _item.get("code_snippet").toString() : "");
						}
					});
				}
			}
		}
		
		@Override
		public int getItemCount() {
			return _data.size();
		}
		
		public class ViewHolder extends RecyclerView.ViewHolder {
			public ViewHolder(View v) {
				super(v);
			}
		}
	}

	// ==================== HELPER METHODS ====================

	private void openBrowser(String url) {
		try {
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		} catch (Exception e) {
			showToast("Cannot open browser: " + e.getMessage());
		}
	}

	private void showToast(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	private String getSearchQuery() {
		if (etSearch == null || etSearch.getText() == null) return "";
		String q = etSearch.getText().toString().trim();
		if (q.equalsIgnoreCase("Edit Text") || q.startsWith("Search dialogs")) {
			return "";
		}
		return q;
	}

	private void _setupSearchAndFilters() {
		if (etSearch != null) {
			String initialText = etSearch.getText() != null ? etSearch.getText().toString().trim() : "";
			if (initialText.equalsIgnoreCase("Edit Text") || initialText.startsWith("Search dialogs")) {
				etSearch.setText("");
			}
			etSearch.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					String query = getSearchQuery();
					if (btnClearSearch != null) {
						btnClearSearch.setVisibility(query.isEmpty() ? View.GONE : View.VISIBLE);
					}
					if (adapter != null) {
						adapter.filter(query, currentCategory);
					}
				}

				@Override
				public void afterTextChanged(Editable s) {}
			});
		}

		if (btnClearSearch != null) {
			btnClearSearch.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (etSearch != null) {
						etSearch.setText("");
					}
				}
			});
		}

		if (btnResetSearch != null) {
			btnResetSearch.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (etSearch != null) {
						etSearch.setText("");
					}
					currentCategory = "All";
					if (chipGroupCategories != null) {
						int allId = chip_all != null ? chip_all.getId() : R.id.chip_all;
						chipGroupCategories.check(allId);
					}
					if (adapter != null) {
						adapter.filter("", "All");
					}
				}
			});
		}

		if (chipGroupCategories == null) {
			int resId = getResources().getIdentifier("chip_group_categories", "id", getPackageName());
			if (resId != 0) {
				chipGroupCategories = findViewById(resId);
			}
		}

		if (chipGroupCategories != null) {
			chipGroupCategories.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
				@Override
				public void onCheckedChanged(ChipGroup group, List<Integer> checkedIds) {
					if (checkedIds.isEmpty()) {
						currentCategory = "All";
					} else {
						int checkedId = checkedIds.get(0);
						Chip chip = findViewById(checkedId);
						if (chip != null) {
							String txt = chip.getText() != null ? chip.getText().toString().trim() : "";
							if (chip == chip_all || txt.equalsIgnoreCase("All") || txt.toLowerCase(Locale.getDefault()).startsWith("all")) {
								currentCategory = "All";
							} else {
								currentCategory = txt;
							}
						} else {
							currentCategory = "All";
						}
					}
					if (adapter != null) {
						adapter.filter(getSearchQuery(), currentCategory);
					}
				}
			});
		}

		View.OnClickListener chipClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (v instanceof Chip) {
					Chip chip = (Chip) v;
					String txt = chip.getText() != null ? chip.getText().toString().trim() : "";
					if (chip == chip_all || txt.equalsIgnoreCase("All") || txt.toLowerCase(Locale.getDefault()).startsWith("all")) {
						currentCategory = "All";
					} else {
						currentCategory = txt;
					}
					if (adapter != null) {
						adapter.filter(getSearchQuery(), currentCategory);
					}
				}
			}
		};

		if (chip_all != null) chip_all.setOnClickListener(chipClickListener);
		if (chip_sheets != null) chip_sheets.setOnClickListener(chipClickListener);
		if (chip_wizards != null) chip_wizards.setOnClickListener(chipClickListener);
		if (chip_animations != null) chip_animations.setOnClickListener(chipClickListener);
		if (chip_sweet != null) chip_sweet.setOnClickListener(chipClickListener);
		if (chip_material != null) chip_material.setOnClickListener(chipClickListener);
		if (chip_inputs != null) chip_inputs.setOnClickListener(chipClickListener);
		if (chip_progress != null) chip_progress.setOnClickListener(chipClickListener);
		if (chip_custom != null) chip_custom.setOnClickListener(chipClickListener);
	}

	public static boolean isCategoryMatch(String itemCategory, String selectedCategory) {
		if (selectedCategory == null) {
			return true;
		}
		String trimmed = selectedCategory.trim();
		if (trimmed.isEmpty() || trimmed.equalsIgnoreCase("All")) {
			return true;
		}
		String cat2 = trimmed.toLowerCase(Locale.getDefault()).replaceAll("[^a-z0-9]", "");
		if (cat2.isEmpty() || cat2.equals("all") || cat2.startsWith("all")) {
			return true;
		}
		if (itemCategory == null) {
			return false;
		}
		if (itemCategory.trim().equalsIgnoreCase(trimmed)) {
			return true;
		}
		String cat1 = itemCategory.toLowerCase(Locale.getDefault()).replaceAll("[^a-z0-9]", "");

		if (cat1.equals(cat2) || cat1.contains(cat2) || cat2.contains(cat1)) {
			return true;
		}

		if ((cat2.contains("sheet") || cat2.contains("bottom")) && cat1.contains("sheet")) {
			return true;
		}

		if ((cat2.contains("wizard") || cat2.contains("otp") || cat2.contains("step")) && 
			(cat1.contains("wizard") || cat1.contains("otp"))) {
			return true;
		}

		if (cat2.contains("anim") && cat1.contains("anim")) {
			return true;
		}

		if (cat2.contains("sweet") && cat1.contains("sweet")) {
			return true;
		}

		if (cat2.contains("material") && cat1.contains("material")) {
			return true;
		}

		if ((cat2.contains("input") || cat2.contains("form")) && 
			(cat1.contains("input") || cat1.contains("form"))) {
			return true;
		}

		if ((cat2.contains("progress") || cat2.contains("load")) && 
			(cat1.contains("progress") || cat1.contains("load"))) {
			return true;
		}

		if ((cat2.contains("picker") || cat2.contains("custom")) && 
			(cat1.contains("picker") || cat1.contains("custom"))) {
			return true;
		}

		return false;
	}

	// ==================== 56 DIALOG DEFINITIONS ====================

	private List<DialogItem> createDialogItems() {
		List<DialogItem> list = new ArrayList<>();

		// ==================== 1. BOTTOM SHEETS (NEW & EXPANDED) ====================

		// 1. MediaPickerBottomSheetDialog
		list.add(new DialogItem("sheet_media_picker", "Media & File Picker Sheet",
				"Bottom sheet with 4-item grid (Camera, Gallery, Documents, Audio) for attachments.",
				"Bottom Sheets", R.drawable.ic_camera,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						MediaPickerBottomSheetDialog.show(activity, new MediaPickerBottomSheetDialog.OnMediaOptionSelectedListener() {
							@Override public void onCamera() { showToast("Opening Camera..."); }
							@Override public void onGallery() { showToast("Opening Gallery..."); }
							@Override public void onDocuments() { showToast("Opening Documents..."); }
							@Override public void onAudio() { showToast("Opening Audio files..."); }
						});
					}
				},
				"MediaPickerBottomSheetDialog.show(context, new MediaPickerBottomSheetDialog.OnMediaOptionSelectedListener() {\n    @Override public void onCamera() {}\n    @Override public void onGallery() {}\n    @Override public void onDocuments() {}\n    @Override public void onAudio() {}\n});"));

		// 2. UserListBottomSheetDialog
		list.add(new DialogItem("sheet_user_list", "Team Member Selector Sheet",
				"Bottom sheet displaying user list with avatars, roles, and single selection radio buttons.",
				"Bottom Sheets", R.drawable.ic_person,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						UserListBottomSheetDialog.show(activity, new UserListBottomSheetDialog.OnUserSelectedListener() {
							@Override
							public void onUserSelected(String userName, String userRole) {
								showToast("Assigned: " + userName + " (" + userRole + ")");
							}
						});
					}
				},
				"UserListBottomSheetDialog.show(context, new UserListBottomSheetDialog.OnUserSelectedListener() {\n    @Override\n    public void onUserSelected(String userName, String userRole) {}\n});"));

		// 3. PaymentMethodBottomSheetDialog
		list.add(new DialogItem("sheet_payment", "Payment Method Checkout Sheet",
				"Bottom sheet with Credit Card, Google Pay / UPI options, total price summary, and Pay button.",
				"Bottom Sheets", R.drawable.ic_payment,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						PaymentMethodBottomSheetDialog.show(activity, new PaymentMethodBottomSheetDialog.OnPaymentConfirmedListener() {
							@Override
							public void onPaymentConfirmed(String method, String amount) {
								showToast("Paid " + amount + " via " + method);
							}
						});
					}
				},
				"PaymentMethodBottomSheetDialog.show(context, new PaymentMethodBottomSheetDialog.OnPaymentConfirmedListener() {\n    @Override\n    public void onPaymentConfirmed(String method, String amount) {}\n});"));

		// 4. FeedbackEmojiBottomSheetDialog
		list.add(new DialogItem("sheet_feedback_emoji", "Emoji Reaction Feedback Sheet",
				"Bottom sheet with 4 emoji cards (😍, 😊, 😐, 😞), suggestions box, and submit action.",
				"Bottom Sheets", R.drawable.ic_star,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						FeedbackEmojiBottomSheetDialog.show(activity, new FeedbackEmojiBottomSheetDialog.OnFeedbackSubmittedListener() {
							@Override
							public void onSubmitted(String emoji, String comment) {
								showToast("Feedback: " + emoji + " - " + comment);
							}
						});
					}
				},
				"FeedbackEmojiBottomSheetDialog.show(context, new FeedbackEmojiBottomSheetDialog.OnFeedbackSubmittedListener() {\n    @Override\n    public void onSubmitted(String emoji, String comment) {}\n});"));

		// 5. ExpandableDetailsBottomSheetDialog
		list.add(new DialogItem("sheet_expandable_details", "Project Specs & Details Sheet",
				"Bottom sheet displaying structured key-value specs, statistics, and dismiss button.",
				"Bottom Sheets", R.drawable.ic_sparkle,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						ExpandableDetailsBottomSheetDialog.show(activity);
					}
				},
				"ExpandableDetailsBottomSheetDialog.show(context);"));

		// 6. ActionBottomSheetDialog
		list.add(new DialogItem("sheet_action", "Action Menu Bottom Sheet",
				"Bottom sheet with drag handle and quick action items (Edit, Share, Copy, Delete).",
				"Bottom Sheets", R.drawable.ic_edit,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						ActionBottomSheetDialog.show(activity, new ActionBottomSheetDialog.OnActionSelectedListener() {
							@Override public void onEdit() { showToast("Edit clicked"); }
							@Override public void onShare() { showToast("Share clicked"); }
							@Override public void onCopy() { showToast("Copy clicked"); }
							@Override public void onDelete() { showToast("Delete clicked"); }
						});
					}
				},
				"ActionBottomSheetDialog.show(context, new ActionBottomSheetDialog.OnActionSelectedListener() {\n    @Override public void onEdit() {}\n    @Override public void onShare() {}\n    @Override public void onCopy() {}\n    @Override public void onDelete() {}\n});"));

		// 7. ShareBottomSheetDialog
		list.add(new DialogItem("sheet_share", "Social Share Bottom Sheet",
				"Modern bottom sheet with social icons (WhatsApp, Telegram, Copy Link).",
				"Bottom Sheets", R.drawable.ic_share,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						ShareBottomSheetDialog.show(activity, new ShareBottomSheetDialog.OnShareOptionSelectedListener() {
							@Override public void onShareWhatsApp() { showToast("Opening WhatsApp..."); }
							@Override public void onShareTelegram() { showToast("Opening Telegram..."); }
							@Override public void onCopyLink() { showToast("Link copied to clipboard!"); }
						});
					}
				},
				"ShareBottomSheetDialog.show(context, new ShareBottomSheetDialog.OnShareOptionSelectedListener() {\n    @Override public void onShareWhatsApp() {}\n    @Override public void onShareTelegram() {}\n    @Override public void onCopyLink() {}\n});"));

		// 8. FilterBottomSheetDialog
		list.add(new DialogItem("sheet_filter", "Filter & Sort Bottom Sheet",
				"Material 3 bottom sheet with filter chips and apply/reset actions.",
				"Bottom Sheets", R.drawable.ic_filter,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						FilterBottomSheetDialog.show(activity, new FilterBottomSheetDialog.OnFilterAppliedListener() {
							@Override
							public void onApply(String sortOption) {
								showToast("Sorted by: " + sortOption);
							}
							@Override
							public void onReset() {
								showToast("Filters reset");
							}
						});
					}
				},
				"FilterBottomSheetDialog.show(context, new FilterBottomSheetDialog.OnFilterAppliedListener() {\n    @Override public void onApply(String sortOption) {}\n    @Override public void onReset() {}\n});"));

		// ==================== 2. WIZARDS, OTP & ANIMATIONS ====================

		// 9. OtpVerificationDialog
		list.add(new DialogItem("otp_verify", "OTP Code Verification",
				"Advanced 4-digit square input boxes with automatic focus jumping, backspace handling, and resend countdown.",
				"Wizards & OTP", R.drawable.ic_lock,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						OtpVerificationDialog.show(activity, new OtpVerificationDialog.OnOtpVerifiedListener() {
							@Override
							public void onVerified(String otpCode) {
								showToast("OTP Verified: " + otpCode);
							}
						});
					}
				},
				"OtpVerificationDialog.show(context, new OtpVerificationDialog.OnOtpVerifiedListener() {\n    @Override\n    public void onVerified(String otpCode) {}\n});"));

		// 10. StepWizardDialog
		list.add(new DialogItem("step_wizard", "Multi-Step Onboarding Wizard",
				"Interactive 3-step setup flow with dot progress indicators, back/next navigation, and completion callback.",
				"Wizards & OTP", R.drawable.ic_arrow_forward,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						StepWizardDialog.show(activity, new StepWizardDialog.OnWizardCompletedListener() {
							@Override
							public void onCompleted() {
								showToast("Onboarding Wizard Completed!");
							}
						});
					}
				},
				"StepWizardDialog.show(context, new StepWizardDialog.OnWizardCompletedListener() {\n    @Override\n    public void onCompleted() {}\n});"));

		// 10B. SetupPermissionWizardDialog
		list.add(new DialogItem("wizard_permissions", "Permissions Setup Wizard",
				"Interactive 3-step setup flow for storage, notification, and background permissions with animated dot indicators.",
				"Wizards & OTP", R.drawable.ic_lock,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						SetupPermissionWizardDialog.show(activity, new SetupPermissionWizardDialog.OnPermissionWizardCompletedListener() {
							@Override
							public void onCompleted() {
								showToast("Permissions Configured Successfully!");
							}
						});
					}
				},
				"SetupPermissionWizardDialog.show(context, new SetupPermissionWizardDialog.OnPermissionWizardCompletedListener() {\n    @Override\n    public void onCompleted() {}\n});"));

		// 10C. AccountSetupWizardDialog
		list.add(new DialogItem("wizard_account", "Profile & Role Setup Wizard",
				"Personalize user role, theme preferences, and onboarding flags with fluid animated step transitions.",
				"Wizards & OTP", R.drawable.ic_person,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						AccountSetupWizardDialog.show(activity, new AccountSetupWizardDialog.OnAccountSetupCompletedListener() {
							@Override
							public void onCompleted(String selectedRole) {
								showToast("Profile Configured: " + selectedRole);
							}
						});
					}
				},
				"AccountSetupWizardDialog.show(context, new AccountSetupWizardDialog.OnAccountSetupCompletedListener() {\n    @Override\n    public void onCompleted(String selectedRole) {}\n});"));

		// 11. AnimatedCelebrationDialog
		list.add(new DialogItem("anim_celebration", "Animated Spring Celebration",
				"Buttery smooth bouncing scale-in animation and pulse badges crafted natively with Android ObjectAnimator.",
				"Animations", R.drawable.ic_trophy,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						AnimatedCelebrationDialog.show(activity, new AnimatedCelebrationDialog.OnCelebrationDismissedListener() {
							@Override
							public void onDismissed() {
								showToast("Celebration reward claimed!");
							}
						});
					}
				},
				"AnimatedCelebrationDialog.show(context, new AnimatedCelebrationDialog.OnCelebrationDismissedListener() {\n    @Override\n    public void onDismissed() {}\n});"));

		// ==================== 2B. XML ANIMATED DIALOGS (res/anim/) ====================

		// 12. PopupZoomDialog
		list.add(new DialogItem("anim_popup", "Popup Zoom Animation",
				"Window enter/exit animation using res/anim/dialog_popup_enter.xml & exit.xml with overshoot scale.",
				"Animations", R.drawable.ic_sparkle,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						PopupZoomDialog.show(activity, new PopupZoomDialog.OnDismissListener() {
							@Override
							public void onDismiss() {
								showToast("Popup Zoom Dismissed");
							}
						});
					}
				},
				"PopupZoomDialog.show(context, new PopupZoomDialog.OnDismissListener() {\n    @Override\n    public void onDismiss() {}\n});"));

		// 13. SmoothFadeDialog
		list.add(new DialogItem("anim_fade", "Smooth Fade Animation",
				"Soft alpha opacity enter/exit animation using res/anim/dialog_fade_enter.xml & exit.xml.",
				"Animations", R.drawable.ic_info,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						SmoothFadeDialog.show(activity, new SmoothFadeDialog.OnDismissListener() {
							@Override
							public void onDismiss() {
								showToast("Smooth Fade Dismissed");
							}
						});
					}
				},
				"SmoothFadeDialog.show(context, new SmoothFadeDialog.OnDismissListener() {\n    @Override\n    public void onDismiss() {}\n});"));

		// 14. SlideUpDialog
		list.add(new DialogItem("anim_slide_up", "Slide Up Animation",
				"Fluid slide up from bottom animation using res/anim/dialog_slide_up_enter.xml & exit.xml.",
				"Animations", R.drawable.ic_check_circle,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						SlideUpDialog.show(activity, new SlideUpDialog.OnActionListener() {
							@Override
							public void onConfirm() {
								showToast("Slide Up Confirmed!");
							}
							@Override
							public void onCancel() {
								showToast("Slide Up Cancelled");
							}
						});
					}
				},
				"SlideUpDialog.show(context, new SlideUpDialog.OnActionListener() {\n    @Override\n    public void onConfirm() {}\n    @Override\n    public void onCancel() {}\n});"));

		// 15. BounceSpringDialog
		list.add(new DialogItem("anim_bounce", "Spring Bounce Animation",
				"Playful bubble pop enter animation using res/anim/dialog_bounce_enter.xml & exit.xml.",
				"Animations", R.drawable.ic_trophy,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						BounceSpringDialog.show(activity, new BounceSpringDialog.OnDismissListener() {
							@Override
							public void onDismiss() {
								showToast("Spring Bounce Dismissed");
							}
						});
					}
				},
				"BounceSpringDialog.show(context, new BounceSpringDialog.OnDismissListener() {\n    @Override\n    public void onDismiss() {}\n});"));

		// 16. RotateScaleDialog
		list.add(new DialogItem("anim_rotate", "Rotate & Scale Animation",
				"Playful -15 degree tilt and scale-in animation using res/anim/dialog_rotate_enter.xml & exit.xml.",
				"Animations", R.drawable.ic_palette,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						RotateScaleDialog.show(activity, new RotateScaleDialog.OnDismissListener() {
							@Override
							public void onDismiss() {
								showToast("Rotate & Scale Dismissed");
							}
						});
					}
				},
				"RotateScaleDialog.show(context, new RotateScaleDialog.OnDismissListener() {\n    @Override\n    public void onDismiss() {}\n});"));

		// ==================== 3. SWEET ALERTS ====================

		list.add(new DialogItem("sweet_success", "Success Alert",
				"Celebratory green badge with positive confirmation and custom action callback.",
				"SweetAlerts", R.drawable.ic_check_circle,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						SweetSuccessDialog.show(activity, "Operation Successful!", "Your settings have been saved to the cloud.", new SweetSuccessDialog.OnConfirmListener() {
							@Override
							public void onConfirm() {
								showToast("Confirmed Success!");
							}
						});
					}
				},
				"SweetSuccessDialog.show(context, \"Success!\", \"Saved successfully.\", new SweetSuccessDialog.OnConfirmListener() {\n    @Override\n    public void onConfirm() {}\n});"));

		list.add(new DialogItem("sweet_warning", "Warning Alert",
				"Amber triangle badge with cautionary prompt and dual interactive buttons.",
				"SweetAlerts", R.drawable.ic_warning,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						SweetWarningDialog.show(activity, "Unsaved Changes", "Are you sure you want to exit without saving?", new SweetWarningDialog.OnActionListener() {
							@Override
							public void onConfirm() {
								showToast("Changes discarded");
							}
							@Override
							public void onCancel() {
								showToast("Canceled");
							}
						});
					}
				},
				"SweetWarningDialog.show(context, \"Warning\", \"Proceed anyway?\", new SweetWarningDialog.OnActionListener() {\n    @Override\n    public void onConfirm() {}\n    @Override\n    public void onCancel() {}\n});"));

		list.add(new DialogItem("sweet_error", "Error Alert",
				"Red danger badge for error notices with immediate retry action.",
				"SweetAlerts", R.drawable.ic_error,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						SweetErrorDialog.show(activity, "Connection Failed", "Unable to establish connection to the server.", new SweetErrorDialog.OnRetryListener() {
							@Override
							public void onRetry() {
								showToast("Retrying connection...");
							}
						});
					}
				},
				"SweetErrorDialog.show(context, \"Error\", \"Something failed.\", new SweetErrorDialog.OnRetryListener() {\n    @Override\n    public void onRetry() {}\n});"));

		list.add(new DialogItem("sweet_info", "Info Alert",
				"Sky-blue info badge for helpful tips, notices, and system announcements.",
				"SweetAlerts", R.drawable.ic_info,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						SweetInfoDialog.show(activity, "Maintenance Scheduled", "Server upgrade will occur tonight at 02:00 AM UTC.", new SweetInfoDialog.OnDismissListener() {
							@Override
							public void onDismiss() {
								showToast("Info acknowledged");
							}
						});
					}
				},
				"SweetInfoDialog.show(context, \"Info\", \"Notice text here.\", new SweetInfoDialog.OnDismissListener() {\n    @Override\n    public void onDismiss() {}\n});"));

		list.add(new DialogItem("sweet_question", "Question / Decision Alert",
				"Purple question mark badge for user choices and confirmations.",
				"SweetAlerts", R.drawable.ic_help,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						SweetQuestionDialog.show(activity, "Sync Data?", "Would you like to sync your offline changes to cloud storage?", new SweetQuestionDialog.OnDecisionListener() {
							@Override
							public void onPositive() {
								showToast("Sync started");
							}
							@Override
							public void onNegative() {
								showToast("Sync postponed");
							}
						});
					}
				},
				"SweetQuestionDialog.show(context, \"Question?\", \"Body message\", new SweetQuestionDialog.OnDecisionListener() {\n    @Override\n    public void onPositive() {}\n    @Override\n    public void onNegative() {}\n});"));

		// ==================== 4. MATERIAL 3 ====================

		list.add(new DialogItem("mat_basic", "Material 3 Basic Alert",
				"Clean MaterialAlertDialogBuilder with positive and negative actions.",
				"Material 3", R.drawable.ic_check_circle,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						MaterialBasicDialog.show(activity, "Standard Alert", "This is built with Google MaterialAlertDialogBuilder.", new MaterialBasicDialog.OnDialogActionListener() {
							@Override
							public void onPositive() {
								showToast("Positive action clicked");
							}
							@Override
							public void onNegative() {
								showToast("Dismissed");
							}
						});
					}
				},
				"MaterialBasicDialog.show(context, \"Title\", \"Message\", new MaterialBasicDialog.OnDialogActionListener() {\n    @Override\n    public void onPositive() {}\n    @Override\n    public void onNegative() {}\n});"));

		list.add(new DialogItem("mat_destructive", "Destructive Confirmation",
				"Material 3 warning dialog highlighting irreversible actions.",
				"Material 3", R.drawable.ic_delete,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						MaterialDestructiveDialog.show(activity, "Delete Project?", "This will remove all files from your device.", new MaterialDestructiveDialog.OnDestructiveActionListener() {
							@Override
							public void onConfirmDestructive() {
								showToast("Project deleted");
							}
							@Override
							public void onCancel() {
								showToast("Cancelled");
							}
						});
					}
				},
				"MaterialDestructiveDialog.show(context, \"Delete?\", \"Irreversible.\", new MaterialDestructiveDialog.OnDestructiveActionListener() {\n    @Override\n    public void onConfirmDestructive() {}\n    @Override\n    public void onCancel() {}\n});"));

		list.add(new DialogItem("mat_single_choice", "Single Choice (Radio List)",
				"Material 3 radio button list with persistent selection.",
				"Material 3", R.drawable.ic_check_circle,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						String[] options = new String[]{"High Quality (1080p)", "Standard Quality (720p)", "Data Saver (480p)"};
						MaterialSingleChoiceDialog.show(activity, "Select Video Quality", options, 0, new MaterialSingleChoiceDialog.OnItemSelectedListener() {
							@Override
							public void onSelected(int index, String item) {
								showToast("Selected: " + item);
							}
						});
					}
				},
				"String[] options = {\"Option 1\", \"Option 2\"};\nMaterialSingleChoiceDialog.show(context, \"Title\", options, 0, new MaterialSingleChoiceDialog.OnItemSelectedListener() {\n    @Override\n    public void onSelected(int index, String item) {}\n});"));

		list.add(new DialogItem("mat_multi_choice", "Multi Choice (Checkbox List)",
				"Material 3 multiple checkbox selection dialog with summary callback.",
				"Material 3", R.drawable.ic_check_circle,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						String[] tags = new String[]{"UI & UX", "Animations", "Networking", "Database", "Security"};
						boolean[] checked = new boolean[]{true, true, false, false, true};
						MaterialMultiChoiceDialog.show(activity, "Filter Topics", tags, checked, new MaterialMultiChoiceDialog.OnMultiChoiceSelectedListener() {
							@Override
							public void onSelected(boolean[] checkedItems, List<String> selectedLabels) {
								showToast("Selected " + selectedLabels.size() + " topics");
							}
						});
					}
				},
				"MaterialMultiChoiceDialog.show(context, \"Topics\", tags, checked, new MaterialMultiChoiceDialog.OnMultiChoiceSelectedListener() {\n    @Override\n    public void onSelected(boolean[] checkedItems, List<String> selectedLabels) {}\n});"));

		list.add(new DialogItem("mat_scrollable", "Scrollable License Agreement",
				"Material 3 dialog with smooth scrolling for long legal text or licenses.",
				"Material 3", R.drawable.ic_sparkle,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						MaterialScrollableDialog.show(activity, "Open Source Licenses", null, new MaterialScrollableDialog.OnAcceptListener() {
							@Override
							public void onAccept() {
								showToast("Licenses accepted");
							}
						});
					}
				},
				"MaterialScrollableDialog.show(context, \"License\", longText, new MaterialScrollableDialog.OnAcceptListener() {\n    @Override\n    public void onAccept() {}\n});"));

		// ==================== 5. INPUT & FORMS ====================

		list.add(new DialogItem("input_prompt", "Text Input Prompt",
				"Text input dialog with floating label, validation, and cancel/save buttons.",
				"Input & Forms", R.drawable.ic_edit,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						InputPromptDialog.show(activity, "New Folder", "My Project", new InputPromptDialog.OnInputSubmittedListener() {
							@Override
							public void onSubmit(String input) {
								showToast("Created: " + input);
							}
						});
					}
				},
				"InputPromptDialog.show(context, \"Title\", \"Default\", new InputPromptDialog.OnInputSubmittedListener() {\n    @Override\n    public void onSubmit(String input) {}\n});"));

		list.add(new DialogItem("input_pin", "PIN Security Code",
				"4-digit numeric PIN password dialog with visibility toggle and validation.",
				"Input & Forms", R.drawable.ic_lock,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						PinSecurityDialog.show(activity, new PinSecurityDialog.OnPinEnteredListener() {
							@Override
							public void onPinEntered(String pin) {
								showToast("PIN Verified: " + pin);
							}
						});
					}
				},
				"PinSecurityDialog.show(context, new PinSecurityDialog.OnPinEnteredListener() {\n    @Override\n    public void onPinEntered(String pin) {}\n});"));

		list.add(new DialogItem("input_rating", "Star Rating & Feedback",
				"5-star interactive rating with comment box and submission callback.",
				"Input & Forms", R.drawable.ic_star,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						RatingFeedbackDialog.show(activity, new RatingFeedbackDialog.OnRatingSubmittedListener() {
							@Override
							public void onSubmit(int stars, String comment) {
								showToast("Rated " + stars + " stars: " + comment);
							}
						});
					}
				},
				"RatingFeedbackDialog.show(context, new RatingFeedbackDialog.OnRatingSubmittedListener() {\n    @Override\n    public void onSubmit(int stars, String comment) {}\n});"));

		list.add(new DialogItem("input_stepper", "Number Stepper Counter",
				"Interactive [-] and [+] stepper with min/max bounds and instant count display.",
				"Input & Forms", R.drawable.ic_add,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						NumberStepperDialog.show(activity, 2, 1, 10, new NumberStepperDialog.OnQuantitySelectedListener() {
							@Override
							public void onQuantitySelected(int quantity) {
								showToast("Selected quantity: " + quantity);
							}
						});
					}
				},
				"NumberStepperDialog.show(context, 1, 1, 10, new NumberStepperDialog.OnQuantitySelectedListener() {\n    @Override\n    public void onQuantitySelected(int quantity) {}\n});"));

		list.add(new DialogItem("input_subscribe", "Newsletter Subscription",
				"Email capture dialog with instant format validation and subscription action.",
				"Input & Forms", R.drawable.ic_email,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						EmailSubscribeDialog.show(activity, new EmailSubscribeDialog.OnSubscribedListener() {
							@Override
							public void onSubscribed(String email) {
								showToast("Subscribed: " + email);
							}
						});
					}
				},
				"EmailSubscribeDialog.show(context, new EmailSubscribeDialog.OnSubscribedListener() {\n    @Override\n    public void onSubscribed(String email) {}\n});"));

		// 6. LoginFormDialog
		list.add(new DialogItem("input_login", "Sign In / Login Form",
				"Complete authentication dialog with email, password toggle, remember me checkbox, and forgot password link.",
				"Input & Forms", R.drawable.ic_person,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						LoginFormDialog.show(activity, new LoginFormDialog.OnLoginListener() {
							@Override
							public void onLogin(String email, String password, boolean rememberMe) {
								showToast("Logged in as " + email + " (Remember: " + rememberMe + ")");
							}
							@Override
							public void onForgotPassword() {
								showToast("Forgot Password clicked");
							}
						});
					}
				},
				"LoginFormDialog.show(context, new LoginFormDialog.OnLoginListener() {\n    @Override\n    public void onLogin(String email, String password, boolean rememberMe) {}\n    @Override\n    public void onForgotPassword() {}\n});"));

		// 7. RegisterFormDialog
		list.add(new DialogItem("input_register", "Account Registration Form",
				"New user onboarding form with full name, email, password, and terms acceptance.",
				"Input & Forms", R.drawable.ic_sparkle,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						RegisterFormDialog.show(activity, new RegisterFormDialog.OnRegisterListener() {
							@Override
							public void onRegister(String name, String email, String password) {
								showToast("Registered user: " + name);
							}
						});
					}
				},
				"RegisterFormDialog.show(context, new RegisterFormDialog.OnRegisterListener() {\n    @Override\n    public void onRegister(String name, String email, String password) {}\n});"));

		// 8. ChangePasswordDialog
		list.add(new DialogItem("input_change_pw", "Change / Reset Password",
				"Security dialog for updating credentials with current, new, and confirmed password validation.",
				"Input & Forms", R.drawable.ic_lock,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						ChangePasswordDialog.show(activity, new ChangePasswordDialog.OnPasswordChangedListener() {
							@Override
							public void onPasswordChanged(String oldPassword, String newPassword) {
								showToast("Password updated successfully!");
							}
						});
					}
				},
				"ChangePasswordDialog.show(context, new ChangePasswordDialog.OnPasswordChangedListener() {\n    @Override\n    public void onPasswordChanged(String oldPassword, String newPassword) {}\n});"));

		// 9. AddressFormDialog
		list.add(new DialogItem("input_address", "Shipping & Billing Address",
				"Multi-field location form with street, city, state, postal code, and country.",
				"Input & Forms", R.drawable.ic_location,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						AddressFormDialog.show(activity, new AddressFormDialog.OnAddressSavedListener() {
							@Override
							public void onAddressSaved(String street, String city, String state, String zip, String country) {
								showToast("Address Saved: " + city + ", " + country);
							}
						});
					}
				},
				"AddressFormDialog.show(context, new AddressFormDialog.OnAddressSavedListener() {\n    @Override\n    public void onAddressSaved(String street, String city, String state, String zip, String country) {}\n});"));

		// 10. ContactSupportDialog
		list.add(new DialogItem("input_support", "Support & Bug Report",
				"Issue reporting form with subject, category selector (Bug, Feature, Help), and detailed description.",
				"Input & Forms", R.drawable.ic_support,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						ContactSupportDialog.show(activity, new ContactSupportDialog.OnSupportTicketSubmittedListener() {
							@Override
							public void onSubmit(String subject, String category, String description) {
								showToast("Submitted ticket [" + category + "]: " + subject);
							}
						});
					}
				},
				"ContactSupportDialog.show(context, new ContactSupportDialog.OnSupportTicketSubmittedListener() {\n    @Override\n    public void onSubmit(String subject, String category, String description) {}\n});"));

		// 11. PromoCouponDialog
		list.add(new DialogItem("input_coupon", "Redeem Promo Coupon",
				"Discount voucher input dialog with inline code verification and instant discount feedback.",
				"Input & Forms", R.drawable.ic_discount,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						PromoCouponDialog.show(activity, new PromoCouponDialog.OnPromoAppliedListener() {
							@Override
							public void onApplied(String couponCode, int discountPercent) {
								showToast("Discount redeemed: " + couponCode + " (" + discountPercent + "% OFF)");
							}
						});
					}
				},
				"PromoCouponDialog.show(context, new PromoCouponDialog.OnPromoAppliedListener() {\n    @Override\n    public void onApplied(String couponCode, int discountPercent) {}\n});"));

		// ==================== 6. PROGRESS & LOADERS ====================

		list.add(new DialogItem("progress_determinate", "Determinate Progress Bar",
				"Live animating progress indicator (0% to 100%) with cancel support.",
				"Progress & Loaders", R.drawable.ic_play,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						ProgressDeterminateDialog.show(activity, new ProgressDeterminateDialog.OnProgressListener() {
							@Override
							public void onComplete() {
								showToast("Download completed successfully!");
							}
							@Override
							public void onCancel() {
								showToast("Download canceled");
							}
						});
					}
				},
				"ProgressDeterminateDialog.show(context, new ProgressDeterminateDialog.OnProgressListener() {\n    @Override public void onComplete() {}\n    @Override public void onCancel() {}\n});"));

		list.add(new DialogItem("progress_spinner", "Indeterminate Loading Spinner",
				"Circular loader indicator with message and auto-dismiss simulation.",
				"Progress & Loaders", R.drawable.ic_clock,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						LoadingSpinnerDialog.show(activity, "Connecting...", "Fetching your latest Sketchware components");
					}
				},
				"LoadingSpinnerDialog.show(context, \"Title\", \"Message\");"));

		list.add(new DialogItem("progress_multistep", "Multi-Step Sync Loader",
				"Progressive multi-step status dialog with checkmarks and active spinner.",
				"Progress & Loaders", R.drawable.ic_sparkle,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						MultiStepLoaderDialog.show(activity);
					}
				},
				"MultiStepLoaderDialog.show(context);"));

		// ==================== 7. PICKERS & CUSTOM ====================

		list.add(new DialogItem("github_community", "GitHub Creator & Repo",
				"Showcase developer profile on GitHub, star project repository, and copy repo link with 1 tap.",
				"Pickers & Custom", R.drawable.ic_github,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						GitHubCommunityDialog.show(activity, GITHUB_PROFILE_URL, GITHUB_PROJECT_URL);
					}
				},
				"GitHubCommunityDialog.show(context, \"https://github.com/your-username\", \"https://github.com/your-username/your-repo\");"));

		list.add(new DialogItem("picker_date", "Material Date Picker",
				"Material 3 calendar date picker with formatted date callback.",
				"Pickers & Custom", R.drawable.ic_calendar,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						if (activity instanceof AppCompatActivity) {
							MaterialDatePickerHelper.show((AppCompatActivity) activity, new MaterialDatePickerHelper.OnDateSelectedListener() {
								@Override
								public void onDateSelected(long timestamp, String formattedDate) {
									showToast("Date: " + formattedDate);
								}
							});
						}
					}
				},
				"MaterialDatePickerHelper.show(activity, new MaterialDatePickerHelper.OnDateSelectedListener() {\n    @Override\n    public void onDateSelected(long timestamp, String formattedDate) {}\n});"));

		list.add(new DialogItem("picker_time", "Material Time Picker",
				"Material 3 interactive clock time picker with 12h/24h support.",
				"Pickers & Custom", R.drawable.ic_clock,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						if (activity instanceof AppCompatActivity) {
							MaterialTimePickerHelper.show((AppCompatActivity) activity, new MaterialTimePickerHelper.OnTimeSelectedListener() {
								@Override
								public void onTimeSelected(int hour, int minute, String formattedTime) {
									showToast("Time: " + formattedTime);
								}
							});
						}
					}
				},
				"MaterialTimePickerHelper.show(activity, new MaterialTimePickerHelper.OnTimeSelectedListener() {\n    @Override\n    public void onTimeSelected(int hour, int minute, String formattedTime) {}\n});"));

		list.add(new DialogItem("picker_color", "Color Palette Picker",
				"Grid of 8 vibrant color circles with hex preview and selection callback.",
				"Pickers & Custom", R.drawable.ic_palette,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						ColorPickerDialog.show(activity, new ColorPickerDialog.OnColorSelectedListener() {
							@Override
							public void onColorSelected(String hexColor, int colorInt) {
								showToast("Chosen Color: " + hexColor);
							}
						});
					}
				},
				"ColorPickerDialog.show(context, new ColorPickerDialog.OnColorSelectedListener() {\n    @Override\n    public void onColorSelected(String hexColor, int colorInt) {}\n});"));

		list.add(new DialogItem("custom_glass", "Glassmorphic Card Dialog",
				"Translucent card styling with subtle borders, glowing icon, and modern aesthetics.",
				"Pickers & Custom", R.drawable.ic_sparkle,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						GlassmorphicDialog.show(activity, new GlassmorphicDialog.OnExploreListener() {
							@Override
							public void onExplore() {
								showToast("Glassmorphism explored!");
							}
						});
					}
				},
				"GlassmorphicDialog.show(context, new GlassmorphicDialog.OnExploreListener() {\n    @Override\n    public void onExplore() {}\n});"));

		list.add(new DialogItem("custom_whatisnew", "What's New in v2.0",
				"Release notes dialog with version badge and bulleted feature highlights.",
				"Pickers & Custom", R.drawable.ic_sparkle,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						WhatIsNewDialog.show(activity);
					}
				},
				"WhatIsNewDialog.show(context);"));

		list.add(new DialogItem("custom_reward", "Achievement & Reward Unlock",
				"Gamified celebration dialog with trophy icon and claim reward button.",
				"Pickers & Custom", R.drawable.ic_trophy,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						RewardUnlockDialog.show(activity, new RewardUnlockDialog.OnRewardClaimedListener() {
							@Override
							public void onClaim() {
								showToast("500 XP & Badge Claimed!");
							}
						});
					}
				},
				"RewardUnlockDialog.show(context, new RewardUnlockDialog.OnRewardClaimedListener() {\n    @Override\n    public void onClaim() {}\n});"));

		list.add(new DialogItem("custom_offline", "No Internet Connection",
				"Offline network warning dialog with instant retry action button.",
				"Pickers & Custom", R.drawable.ic_wifi_off,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						NoInternetDialog.show(activity, new NoInternetDialog.OnRetryListener() {
							@Override
							public void onRetry() {
								showToast("Checking connection...");
							}
						});
					}
				},
				"NoInternetDialog.show(context, new NoInternetDialog.OnRetryListener() {\n    @Override\n    public void onRetry() {}\n});"));

		list.add(new DialogItem("custom_profile", "Developer Profile & About",
				"Showcase creator profile with avatar, biography, and portfolio links.",
				"Pickers & Custom", R.drawable.ic_person,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						DeveloperProfileDialog.show(activity, new DeveloperProfileDialog.OnContactListener() {
							@Override
							public void onContact() {
								showToast("Opening Portfolio...");
							}
						});
					}
				},
				"DeveloperProfileDialog.show(context, new DeveloperProfileDialog.OnContactListener() {\n    @Override\n    public void onContact() {}\n});"));

		list.add(new DialogItem("custom_delete", "Delete Item Confirmation",
				"Destructive trash dialog with danger red button and cancellation.",
				"Pickers & Custom", R.drawable.ic_delete,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						DeleteConfirmDialog.show(activity, new DeleteConfirmDialog.OnDeleteConfirmedListener() {
							@Override
							public void onDelete() {
								showToast("Item permanently deleted");
							}
						});
					}
				},
				"DeleteConfirmDialog.show(context, new DeleteConfirmDialog.OnDeleteConfirmedListener() {\n    @Override\n    public void onDelete() {}\n});"));

		list.add(new DialogItem("custom_permission", "Permissions Request",
				"Feature permission disclosure dialog with checklist items.",
				"Pickers & Custom", R.drawable.ic_lock,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						PermissionRequestDialog.show(activity, new PermissionRequestDialog.OnPermissionDecisionListener() {
							@Override
							public void onGrant() {
								showToast("Permissions granted");
							}
							@Override
							public void onDeny() {
								showToast("Permissions skipped");
							}
						});
					}
				},
				"PermissionRequestDialog.show(context, new PermissionRequestDialog.OnPermissionDecisionListener() {\n    @Override\n    public void onGrant() {}\n    @Override\n    public void onDeny() {}\n});"));

		list.add(new DialogItem("custom_notification", "Push Notifications Prompt",
				"Notification permission banner dialog with opt-in and skip buttons.",
				"Pickers & Custom", R.drawable.ic_notification,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						CustomNotificationDialog.show(activity, new CustomNotificationDialog.OnNotificationToggleListener() {
							@Override
							public void onEnable() {
								showToast("Notifications enabled!");
							}
							@Override
							public void onSkip() {
								showToast("Notifications skipped");
							}
						});
					}
				},
				"CustomNotificationDialog.show(context, new CustomNotificationDialog.OnNotificationToggleListener() {\n    @Override\n    public void onEnable() {}\n    @Override\n    public void onSkip() {}\n});"));

		list.add(new DialogItem("custom_language", "Language Selection",
				"Locale selector dialog with radio options (English, Spanish, French, Hindi).",
				"Pickers & Custom", R.drawable.ic_language,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						LanguageSelectDialog.show(activity, new LanguageSelectDialog.OnLanguageSelectedListener() {
							@Override
							public void onSelected(String languageCode, String languageName) {
								showToast("Language set to " + languageName);
							}
						});
					}
				},
				"LanguageSelectDialog.show(context, new LanguageSelectDialog.OnLanguageSelectedListener() {\n    @Override\n    public void onSelected(String code, String name) {}\n});"));

		list.add(new DialogItem("custom_battery", "Battery Optimization",
				"Battery saver dialog explaining resource savings and performance mode.",
				"Pickers & Custom", R.drawable.ic_battery,
				new DialogItem.DialogLauncher() {
					@Override
					public void launch(Activity activity) {
						BatterySaverDialog.show(activity, new BatterySaverDialog.OnBatterySaverDecisionListener() {
							@Override
							public void onEnable() {
								showToast("Battery saver mode active");
							}
							@Override
							public void onDismiss() {
								showToast("Dismissed");
							}
						});
					}
				},
				"BatterySaverDialog.show(context, new BatterySaverDialog.OnBatterySaverDecisionListener() {\n    @Override\n    public void onEnable() {}\n    @Override\n    public void onDismiss() {}\n});"));

		return list;
	}

	// ==================== NESTED MODEL: DialogItem ====================
	public static class DialogItem {
		private String id;
		private String title;
		private String description;
		private String category;
		private int iconResId;
		private DialogLauncher launcher;
		private String codeSnippet;
		private String javaFile;
		private String layoutXml;
		private String drawables;
		private String animXml;

		public interface DialogLauncher {
			void launch(Activity activity);
		}

		public DialogItem(String id, String title, String description, String category, int iconResId, DialogLauncher launcher, String codeSnippet) {
			this(id, title, description, category, iconResId, launcher, codeSnippet, null, null, null, null);
		}

		public DialogItem(String id, String title, String description, String category, int iconResId, DialogLauncher launcher, String codeSnippet,
						  String javaFile, String layoutXml, String drawables, String animXml) {
			this.id = id;
			this.title = title;
			this.description = description;
			this.category = category;
			this.iconResId = iconResId;
			this.launcher = launcher;
			this.codeSnippet = codeSnippet;
			this.javaFile = javaFile;
			this.layoutXml = layoutXml;
			this.drawables = drawables;
			this.animXml = animXml;
		}

		public String getId() {
			return id;
		}

		public String getTitle() {
			return title;
		}

		public String getDescription() {
			return description;
		}

		public String getCategory() {
			return category;
		}

		public int getIconResId() {
			return iconResId;
		}

		public DialogLauncher getLauncher() {
			return launcher;
		}

		public String getCodeSnippet() {
			return codeSnippet;
		}

		public String getJavaFile() {
			if (javaFile != null) return javaFile;
			return DialogResourceRegistry.getJavaFile(id);
		}

		public String getLayoutXml() {
			if (layoutXml != null) return layoutXml;
			return DialogResourceRegistry.getLayoutXml(id);
		}

		public String getDrawables() {
			if (drawables != null) return drawables;
			return DialogResourceRegistry.getDrawables(id);
		}

		public String getAnimXml() {
			if (animXml != null) return animXml;
			return DialogResourceRegistry.getAnimXml(id);
		}
	}

	// ==================== NESTED ADAPTER: DialogAdapter ====================
	public static class DialogAdapter extends RecyclerView.Adapter<DialogAdapter.DialogViewHolder> {

		private final Activity activity;
		private final List<DialogItem> allItems;
		private final List<DialogItem> filteredList;

		public interface OnFilteredCountChangedListener {
			void onCountChanged(int count);
		}

		private OnFilteredCountChangedListener countListener;

		public DialogAdapter(Activity activity, List<DialogItem> items) {
			this.activity = activity;
			this.allItems = new ArrayList<>(items);
			this.filteredList = new ArrayList<>(items);
			if (activity instanceof MainActivity) {
				((MainActivity) activity)._setupGitHubSection();
				((MainActivity) activity)._setupSearchAndFilters();
			}
		}

		public void setCountListener(OnFilteredCountChangedListener listener) {
			this.countListener = listener;
		}

		public void filter(String query, String category) {
			filteredList.clear();
			String q = query != null ? query.trim().toLowerCase(Locale.getDefault()) : "";

			for (DialogItem item : allItems) {
				boolean matchesCategory = isCategoryMatch(item.getCategory(), category);
				boolean matchesQuery = q.isEmpty()
						|| (item.getTitle() != null && item.getTitle().toLowerCase(Locale.getDefault()).contains(q))
						|| (item.getDescription() != null && item.getDescription().toLowerCase(Locale.getDefault()).contains(q))
						|| (item.getCategory() != null && item.getCategory().toLowerCase(Locale.getDefault()).contains(q));

				if (matchesCategory && matchesQuery) {
					filteredList.add(item);
				}
			}

			notifyDataSetChanged();
			if (countListener != null) {
				countListener.onCountChanged(filteredList.size());
			}
		}

		@NonNull
		@Override
		public DialogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dialog_card, parent, false);
			ViewGroup.LayoutParams lp = view.getLayoutParams();
			if (lp == null) {
				lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			} else {
				lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
			}
			view.setLayoutParams(lp);
			return new DialogViewHolder(view);
		}

		@Override
		public void onBindViewHolder(@NonNull DialogViewHolder holder, int position) {
			ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
			if (lp != null && lp.height != ViewGroup.LayoutParams.WRAP_CONTENT) {
				lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
				holder.itemView.setLayoutParams(lp);
			}

			final DialogItem item = filteredList.get(position);

			holder.tvTitle.setText(item.getTitle());
			holder.tvDescription.setText(item.getDescription());
			holder.tvCategory.setText(item.getCategory());
			holder.ivIcon.setImageResource(item.getIconResId());

			holder.btnLaunch.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (item.getLauncher() != null) {
						item.getLauncher().launch(activity);
					}
				}
			});

			View.OnClickListener cardClickListener = new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (item.getLauncher() != null) {
						item.getLauncher().launch(activity);
					}
				}
			};

			if (holder.cardBase != null) {
				holder.cardBase.setOnClickListener(cardClickListener);
			}
			holder.itemView.setOnClickListener(cardClickListener);

			holder.btnCode.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					CodeSnippetDialog.show(activity,
							item.getTitle(),
							item.getJavaFile(),
							item.getLayoutXml(),
							item.getDrawables(),
							item.getAnimXml(),
							item.getCodeSnippet());
				}
			});
		}

		@Override
		public int getItemCount() {
			return filteredList.size();
		}

		public static class DialogViewHolder extends RecyclerView.ViewHolder {
			final View cardBase;
			final TextView tvTitle;
			final TextView tvDescription;
			final TextView tvCategory;
			final ImageView ivIcon;
			final MaterialButton btnLaunch;
			final MaterialButton btnCode;

			public DialogViewHolder(@NonNull View itemView) {
				super(itemView);
				View b = itemView.findViewById(R.id.base);
				cardBase = b != null ? b : itemView;
				tvTitle = itemView.findViewById(R.id.card_title);
				tvDescription = itemView.findViewById(R.id.card_description);
				tvCategory = itemView.findViewById(R.id.card_category);
				ivIcon = itemView.findViewById(R.id.card_icon);
				btnLaunch = itemView.findViewById(R.id.card_btn_launch);
				btnCode = itemView.findViewById(R.id.card_btn_code);
			}
		}
	}
}